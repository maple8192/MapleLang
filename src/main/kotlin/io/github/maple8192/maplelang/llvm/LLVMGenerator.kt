package io.github.maple8192.maplelang.llvm

import io.github.maple8192.maplelang.parser.node.Function
import io.github.maple8192.maplelang.parser.node.Node
import io.github.maple8192.maplelang.parser.node.Program
import io.github.maple8192.maplelang.parser.node.Statements
import io.github.maple8192.maplelang.parser.node.type.Type
import io.github.maple8192.maplelang.util.Ref

/**
 * This class generates LLVM-IR code.
 */
class LLVMGenerator {
    /**
     * Generates code.
     */
    fun generate(program: Program): List<String> {
        val code = mutableListOf<String>()

        for (function in program.functions) {
            code.addAll(genFunction(function))
        }

        return code
    }

    private fun genFunction(function: Function): List<String> {
        val code = mutableListOf<String>()

        code.add("define ${function.returnType.str} @${function.name}$${funcArgs(function.variables.take(function.argsNum))}(${genFuncArgs(function.variables.take(function.argsNum))}) {")
        code.add("entry:")
        function.variables.forEachIndexed { i, t ->
            code.add("  %${i} = alloca ${t.str}")
            code.add("  store ${t.str} ${if (i < function.argsNum) "%arg${i}" else "0"}, ${t.str}* %${i}")
        }

        code.addAll(genStatement(function.statement, ArrayDeque(), Ref(function.variables.size), Ref(0)))

        code.add("  ret ${function.returnType.str}${when (function.returnType) { Type.Int -> " 0"; Type.Float -> " 0.0"; Type.Void -> "" }}")
        code.add("}")

        return code
    }

    private fun genStatement(statement: Statements, stack: ArrayDeque<Int>, reg: Ref<Int>, label: Ref<Int>): List<String> {
        val code = mutableListOf<String>()

        when (statement) {
            is Statements.Statement -> {
                code.addAll(genNode(statement.node, stack, reg, label))
                stack.removeLast()
            }
            is Statements.Block -> {
                for (s in statement.statements) {
                    code.addAll(genStatement(s, stack, reg, label))
                }
            }
            is Statements.Return -> {
                code.addAll(genNode(statement.node, stack, reg, label))
                code.add("  ret ${statement.node.type.str} %${stack.removeLast()}")
                reg.value++
            }
            is Statements.If -> {
                val l = label.value++
                code.addAll(genNode(statement.condition, stack, reg, label))
                code.add("  %${reg.value} = icmp ne i64 %${stack.removeLast()}, 0")
                code.add("  br i1 %${reg.value++}, label %then${l}, label %else${l}")
                code.add("then${l}:")
                code.addAll(genStatement(statement.trueCase, stack, reg, label))
                code.add("  br label %end${l}")
                code.add("else${l}:")
                statement.falseCase?.also { code.addAll(genStatement(it, stack, reg, label)) }
                code.add("  br label %end${l}")
                code.add("end${l}:")
            }
            is Statements.For -> {
                val l = label.value++
                statement.init?.also { code.addAll(genStatement(Statements.Statement(it), stack, reg, label)) }
                code.add("  br label %begin${l}")
                code.add("begin${l}:")
                if (statement.condition != null) {
                    code.addAll(genNode(statement.condition, stack, reg, label))
                    code.add("  %${reg.value} = icmp ne i64 %${stack.removeLast()}, 0")
                    code.add("  br i1 %${reg.value++}, label %then${l}, label %end${l}")
                } else {
                    code.add("  br label %then${l}")
                }
                code.add("then${l}:")
                code.addAll(genStatement(statement.statement, stack, reg, label))
                statement.update?.also { code.addAll(genStatement(Statements.Statement(it), stack, reg, label)) }
                code.add("  br label %begin${l}")
                code.add("end${l}:")
            }
            is Statements.While -> {
                val l = label.value++
                code.add("  br label %begin${l}")
                code.add("begin${l}:")
                code.addAll(genNode(statement.condition, stack, reg, label))
                code.add("  %${reg.value} = icmp i64 %${stack.removeLast()}, 0")
                code.add("  br i1 %${reg.value++}, label %then${l}, label %end${l}")
                code.add("then${l}:")
                code.addAll(genStatement(statement.statement, stack, reg, label))
                code.add("  br label %begin${l}")
                code.add("end${l}:")
            }
            is Statements.Loop -> {
                val l = label.value++
                code.add("  br label %begin${l}")
                code.add("begin${l}:")
                code.addAll(genStatement(statement.statement, stack, reg, label))
                code.add("  br label %begin${l}")
                code.add("end${l}:")
            }
        }

        return code
    }

    private fun genNode(node: Node, stack: ArrayDeque<Int>, reg: Ref<Int>, label: Ref<Int>): List<String> {
        val code = mutableListOf<String>()

        when (node) {
            is Node.Assign -> {
                code.addAll(genNode(node.node, stack, reg, label))
                code.add("  store ${node.type.str} %${stack.removeLast()}, ${node.type.str}* %${node.offset}")
                code.add("  %${reg.value} = load ${node.type.str}, ${node.type.str}* %${node.offset}")
                stack.add(reg.value++)
            }
            is Node.Exch -> {
                val lTemp = reg.value++
                val rTemp = reg.value++
                code.add("  %${lTemp} = load ${node.type.str}, ${node.type.str}* %${node.lhsOffset}")
                code.add("  %${rTemp} = load ${node.type.str}, ${node.type.str}* %${node.rhsOffset}")
                code.add("  store ${node.type.str} %${rTemp}, ${node.type.str}* %${node.lhsOffset}")
                code.add("  store ${node.type.str} %${lTemp}, ${node.type.str}* %${node.rhsOffset}")
                code.add("  %${reg.value} = load ${node.type.str}, ${node.type.str}* %${node.lhsOffset}")
                stack.add(reg.value++)
            }
            is Node.ChMin -> {}
            is Node.ChMax -> {}
            is Node.Variable -> {
                code.add("  %${reg.value} = load ${node.type.str}, ${node.type.str}* %${node.offset}")
                stack.add(reg.value++)
            }
            is Node.FnCall -> {
                val arguments = mutableListOf<Pair<Int, Type>>()
                for (a in node.args) {
                    code.addAll(genNode(a, stack, reg, label))
                    arguments.add(stack.removeLast() to a.type)
                }
                if (node.type != Type.Void) {
                    code.add("  %${reg.value} = call ${node.type.str} @${node.funcName}(${genFuncCallArgs(arguments)})")
                    stack.add(reg.value++)
                } else {
                    code.add("  call void @${node.funcName}(${genFuncCallArgs(arguments)})")
                    code.add("  %${reg.value} = alloca i64")
                    code.add("  store i64 0, i64* %${reg.value}")
                    stack.add(reg.value++)
                }
            }
            is Node.Number -> {
                code.add("  %${reg.value} = add ${node.type.str} ${node.number}, 0")
                stack.add(reg.value++)
            }
        }

        return code
    }

    private fun genFuncArgs(args: List<Type>): String {
        val code = StringBuilder()
        var first = true
        for (i in args.indices) {
            if (!first) code.append(", ")
            first = false

            code.append("${args[i].str} %arg${i}")
        }
        return code.toString()
    }

    private fun genFuncCallArgs(args: List<Pair<Int, Type>>): String {
        val code = StringBuilder()
        var first = true
        for (a in args) {
            if (!first) code.append(", ")
            first = false

            code.append("${a.second.str} %${a.first}")
        }
        return code.toString()
    }

    private fun funcArgs(args: List<Type>): String {
        return args.joinToString(separator = "_") { it.str }
    }
}