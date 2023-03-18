package io.github.maple8192.maplelang.parser

import io.github.maple8192.maplelang.exception.TokenException
import io.github.maple8192.maplelang.parser.node.Function
import io.github.maple8192.maplelang.parser.node.Node
import io.github.maple8192.maplelang.parser.node.Program
import io.github.maple8192.maplelang.parser.node.Statements
import io.github.maple8192.maplelang.parser.node.type.Type
import io.github.maple8192.maplelang.tokenizer.token.Token
import io.github.maple8192.maplelang.tokenizer.token.type.SymbolType
import io.github.maple8192.maplelang.tokenizer.token.type.WordType

/**
 * This class parses the program.
 */
class Parser(tokenList: List<Token>) {
    private val tokens = TokenQueue(tokenList)

    private val functions = mutableListOf<Triple<String, List<Type>, Type>>().also {
        it.add(Triple("op_add", listOf(Type.Int, Type.Int), Type.Int))
        it.add(Triple("op_add", listOf(Type.Int, Type.Float), Type.Int))
        it.add(Triple("op_add", listOf(Type.Float, Type.Int), Type.Int))
        it.add(Triple("op_add", listOf(Type.Float, Type.Float), Type.Float))
        it.add(Triple("op_sub", listOf(Type.Int, Type.Int), Type.Int))
        it.add(Triple("op_sub", listOf(Type.Int, Type.Float), Type.Int))
        it.add(Triple("op_sub", listOf(Type.Float, Type.Int), Type.Int))
        it.add(Triple("op_sub", listOf(Type.Float, Type.Float), Type.Float))
        it.add(Triple("op_mul", listOf(Type.Int, Type.Int), Type.Int))
        it.add(Triple("op_mul", listOf(Type.Int, Type.Float), Type.Int))
        it.add(Triple("op_mul", listOf(Type.Float, Type.Int), Type.Int))
        it.add(Triple("op_mul", listOf(Type.Float, Type.Float), Type.Float))
        it.add(Triple("op_div", listOf(Type.Int, Type.Int), Type.Int))
        it.add(Triple("op_div", listOf(Type.Int, Type.Float), Type.Int))
        it.add(Triple("op_div", listOf(Type.Float, Type.Int), Type.Int))
        it.add(Triple("op_div", listOf(Type.Float, Type.Float), Type.Float))
        it.add(Triple("op_rem", listOf(Type.Int, Type.Int), Type.Int))
        it.add(Triple("op_rem", listOf(Type.Int, Type.Float), Type.Int))
        it.add(Triple("op_rem", listOf(Type.Float, Type.Int), Type.Int))
        it.add(Triple("op_rem", listOf(Type.Float, Type.Float), Type.Float))
        it.add(Triple("op_le", listOf(Type.Int, Type.Int), Type.Int))
        it.add(Triple("op_le", listOf(Type.Int, Type.Float), Type.Int))
        it.add(Triple("op_le", listOf(Type.Float, Type.Int), Type.Int))
        it.add(Triple("op_le", listOf(Type.Float, Type.Float), Type.Float))
        it.add(Triple("op_lnot", listOf(Type.Int, Type.Int), Type.Int))
        it.add(Triple("op_lnot", listOf(Type.Int, Type.Float), Type.Int))
        it.add(Triple("op_lnot", listOf(Type.Float, Type.Int), Type.Int))
        it.add(Triple("op_lnot", listOf(Type.Float, Type.Float), Type.Float))
    }

    @Throws(TokenException::class)
    fun parse(): Program {
        return program()
    }

    private fun program(): Program {
        val fn = mutableListOf<Function>()

        while (tokens.currentToken !is Token.Eof) {
            fn.add(function())
        }

        return Program(fn.toList())
    }

    private fun function(): Function {
        tokens.expectWord(WordType.Func)
        val funcName = tokens.expectIdent()
        val variables = mutableListOf<Pair<String, Type>>()
        val args = mutableListOf<Type>()
        if (tokens.consumeSymbol(SymbolType.OSquare)) {
            var first = true
            while (!tokens.consumeSymbol(SymbolType.CSquare)) {
                if (!first) tokens.expectSymbol(SymbolType.Comma)
                first = false

                val type = tokens.expectType()
                val name = tokens.expectIdent()

                args.add(type)
                variables.add(name to type)
            }
        }
        val retType = tokens.consumeType() ?: Type.Void
        functions.add(Triple("fn_${funcName}", args.toList(), retType))
        val statement = statement(variables)
        return Function("fn_${funcName}", args.size, variables.map { it.second }, retType, statement)
    }

    private fun statement(variables: MutableList<Pair<String, Type>>): Statements {
        return if (tokens.consumeSymbol(SymbolType.OBrace)) {
            val statements = mutableListOf<Statements>()
            while (!tokens.consumeSymbol(SymbolType.CBrace)) {
                statements.add(statement(variables))
            }
            Statements.Block(statements.toList())
        } else if (tokens.consumeWord(WordType.Return)) {
            val expression = expression(variables)
            tokens.expectSymbol(SymbolType.End)
            Statements.Return(expression)
        } else if (tokens.consumeWord(WordType.If)) {
            val condition = expression(variables)
            val trueCase = statement(variables)
            val falseCase = if (tokens.consumeWord(WordType.Else)) {
                statement(variables)
            } else {
                null
            }
            Statements.If(condition, trueCase, falseCase)
        } else if (tokens.consumeWord(WordType.For)) {
            val init = if (tokens.consumeSymbol(SymbolType.End)) {
                null
            } else {
                val expr = expression(variables)
                tokens.expectSymbol(SymbolType.End)
                expr
            }
            val condition = if (tokens.consumeSymbol(SymbolType.End)) {
                null
            } else {
                val expr = expression(variables)
                tokens.expectSymbol(SymbolType.End)
                expr
            }
            val update = if (tokens.consumeSymbol(SymbolType.End)) {
                null
            } else {
                val expr = expression(variables)
                tokens.expectSymbol(SymbolType.End)
                expr
            }
            val statement = statement(variables)
            Statements.For(init, condition, update, statement)
        } else if (tokens.consumeWord(WordType.While)) {
            val condition = expression(variables)
            val statement = statement(variables)
            Statements.While(condition, statement)
        } else if (tokens.consumeWord(WordType.Loop)) {
            val statement = statement(variables)
            Statements.Loop(statement)
        } else {
            val type = tokens.consumeType()
            if (type != null) {
                val statements = mutableListOf<Statements>()
                var first = true
                while (!tokens.consumeSymbol(SymbolType.End)) {
                    if (!first) tokens.expectSymbol(SymbolType.Comma)
                    first = false

                    val varName = tokens.expectIdent()
                    if (variables.find { it.first == varName } != null) throw TokenException(tokens.prevToken.line, tokens.prevToken.pos, "")
                    variables.add(varName to type)
                    if (tokens.consumeSymbol(SymbolType.Assign)) {
                        val expression = expression(variables)
                        statements.add(Statements.Statement(Node.Assign(type, variables.indexOfFirst { it.first == varName }, expression)))
                    }
                }
                Statements.Block(statements)
            } else {
                val expression = expression(variables)
                tokens.expectSymbol(SymbolType.End)
                Statements.Statement(expression)
            }
        }
    }

    private fun expression(variables: MutableList<Pair<String, Type>>): Node {
        return exchange(variables)
    }

    private fun exchange(variables: MutableList<Pair<String, Type>>): Node {
        val node = assign(variables)

        return if (node is Node.Variable) {
            if (tokens.consumeSymbol(SymbolType.Exch)) {
                val rhs = assign(variables)
                if (rhs !is Node.Variable) throw TokenException(tokens.prevToken.line, tokens.prevToken.pos, "Must be Variable")
                Node.Exch(node.type, node.offset, rhs.offset)
            } else {
                node
            }
        } else {
            node
        }
    }

    private fun assign(variables: MutableList<Pair<String, Type>>): Node {
        val node = or(variables)

        return if (node is Node.Variable) {
            if (tokens.consumeSymbol(SymbolType.Assign)) {
                Node.Assign(node.type, node.offset, assign(variables))
            } else if (tokens.consumeSymbol(SymbolType.AAdd)) {
                Node.Assign(node.type, node.offset, opCall("add", listOf(node, assign(variables))))
            } else if (tokens.consumeSymbol(SymbolType.ASub)) {
                Node.Assign(node.type, node.offset, opCall("sub", listOf(node, assign(variables))))
            } else if (tokens.consumeSymbol(SymbolType.AMul)) {
                Node.Assign(node.type, node.offset, opCall("mul", listOf(node, assign(variables))))
            } else if (tokens.consumeSymbol(SymbolType.ADiv)) {
                Node.Assign(node.type, node.offset, opCall("div", listOf(node, assign(variables))))
            } else if (tokens.consumeSymbol(SymbolType.ARem)) {
                Node.Assign(node.type, node.offset, opCall("rem", listOf(node, assign(variables))))
            } else if (tokens.consumeSymbol(SymbolType.APow)) {
                Node.Assign(node.type, node.offset, opCall("pow", listOf(node, assign(variables))))
            } else if (tokens.consumeSymbol(SymbolType.ARoot)) {
                Node.Assign(node.type, node.offset, opCall("root", listOf(node, assign(variables))))
            } else if (tokens.consumeSymbol(SymbolType.AAnd)) {
                Node.Assign(node.type, node.offset, opCall("and", listOf(node, assign(variables))))
            } else if (tokens.consumeSymbol(SymbolType.AXor)) {
                Node.Assign(node.type, node.offset, opCall("xor", listOf(node, assign(variables))))
            } else if (tokens.consumeSymbol(SymbolType.AOr)) {
                Node.Assign(node.type, node.offset, opCall("or", listOf(node, assign(variables))))
            } else if (tokens.consumeSymbol(SymbolType.ALSft)) {
                Node.Assign(node.type, node.offset, opCall("shl", listOf(node, assign(variables))))
            } else if (tokens.consumeSymbol(SymbolType.ARSft)) {
                Node.Assign(node.type, node.offset, opCall("shr", listOf(node, assign(variables))))
            } else if (tokens.consumeSymbol(SymbolType.ChMin)) {
                Node.ChMin(node.type, node.offset, assign(variables))
            } else if (tokens.consumeSymbol(SymbolType.ChMax)) {
                Node.ChMax(node.type, node.offset, assign(variables))
            } else {
                node
            }
        } else {
            node
        }
    }

    private fun or(variables: MutableList<Pair<String, Type>>): Node {
        val node = and(variables)

        return if (tokens.consumeSymbol(SymbolType.LOr)) {
            opCall("lor", listOf(node, and(variables)))
        } else {
            node
        }
    }

    private fun and(variables: MutableList<Pair<String, Type>>): Node {
        val node = bitOr(variables)

        return if (tokens.consumeSymbol(SymbolType.LAnd)) {
            opCall("land", listOf(node, bitOr(variables)))
        } else {
            node
        }
    }

    private fun bitOr(variables: MutableList<Pair<String, Type>>): Node {
        val node = bitXor(variables)

        return if (tokens.consumeSymbol(SymbolType.BOr)) {
            opCall("or", listOf(node, bitXor(variables)))
        } else {
            node
        }
    }

    private fun bitXor(variables: MutableList<Pair<String, Type>>): Node {
        val node = bitAnd(variables)

        return if (tokens.consumeSymbol(SymbolType.BXor)) {
            opCall("xor", listOf(node, bitAnd(variables)))
        } else {
            node
        }
    }

    private fun bitAnd(variables: MutableList<Pair<String, Type>>): Node {
        val node = equality(variables)

        return if (tokens.consumeSymbol(SymbolType.BAnd)) {
            opCall("and", listOf(node, equality(variables)))
        } else {
            node
        }
    }

    private fun equality(variables: MutableList<Pair<String, Type>>): Node {
        val node = relational(variables)

        return if (tokens.consumeSymbol(SymbolType.Eq)) {
            opCall("eq", listOf(node, relational(variables)))
        } else if (tokens.consumeSymbol(SymbolType.NEq)) {
            opCall("ne", listOf(node, relational(variables)))
        } else {
            node
        }
    }

    private fun relational(variables: MutableList<Pair<String, Type>>): Node {
        val node = shift(variables)

        return if (tokens.consumeSymbol(SymbolType.Less)) {
            opCall("le", listOf(node, relational(variables)))
        } else if (tokens.consumeSymbol(SymbolType.ELess)) {
            opCall("lnot", listOf(opCall("le", listOf(relational(variables), node))))
        } else if (tokens.consumeSymbol(SymbolType.Greater)) {
            opCall("le", listOf(relational(variables), node))
        } else if (tokens.consumeSymbol(SymbolType.EGreater)) {
            opCall("lnot", listOf(opCall("le", listOf(node, relational(variables)))))
        } else {
            node
        }
    }

    private fun shift(variables: MutableList<Pair<String, Type>>): Node {
        val node = add(variables)

        return if (tokens.consumeSymbol(SymbolType.LSft)) {
            opCall("shl", listOf(node, add(variables)))
        } else if (tokens.consumeSymbol(SymbolType.RSft)) {
            opCall("shr", listOf(node, add(variables)))
        } else {
            node
        }
    }

    private fun add(variables: MutableList<Pair<String, Type>>): Node {
        val node = mul(variables)

        return if (tokens.consumeSymbol(SymbolType.Add)) {
            opCall("add", listOf(node, mul(variables)))
        } else if (tokens.consumeSymbol(SymbolType.Sub)) {
            opCall("sub", listOf(node, mul(variables)))
        } else {
            node
        }
    }

    private fun mul(variables: MutableList<Pair<String, Type>>): Node {
        val node = powerAndRoot(variables)

        return if (tokens.consumeSymbol(SymbolType.Mul)) {
            opCall("mul", listOf(node, powerAndRoot(variables)))
        } else if (tokens.consumeSymbol(SymbolType.Div)) {
            opCall("div", listOf(node, powerAndRoot(variables)))
        } else {
            node
        }
    }

    private fun powerAndRoot(variables: MutableList<Pair<String, Type>>): Node {
        val node = unary(variables)

        return if (tokens.consumeSymbol(SymbolType.Pow)) {
            opCall("pow", listOf(node, unary(variables)))
        } else if (tokens.consumeSymbol(SymbolType.Root)) {
            opCall("root", listOf(node, unary(variables)))
        } else {
            node
        }
    }

    private fun unary(variables: MutableList<Pair<String, Type>>): Node {
        return if (tokens.consumeSymbol(SymbolType.Sub)) {
            opCall("minus", listOf(primary(variables)))
        } else if (tokens.consumeSymbol(SymbolType.BNot)) {
            opCall("not", listOf(primary(variables)))
        } else if (tokens.consumeSymbol(SymbolType.LNot)) {
            opCall("lnot", listOf(primary(variables)))
        } else {
            primary(variables)
        }
    }

    private fun primary(variables: MutableList<Pair<String, Type>>): Node {
        return if (tokens.consumeSymbol(SymbolType.OBracket)) {
            val expression = expression(variables)
            tokens.expectSymbol(SymbolType.CBracket)
            expression
        } else if (tokens.currentToken is Token.Ident) {
            val ident = tokens.expectIdent()
            if (tokens.consumeSymbol(SymbolType.OBracket)) {
                val args = mutableListOf<Node>()
                var first = true
                while (!tokens.consumeSymbol(SymbolType.CBracket)) {
                    if (!first) tokens.expectSymbol(SymbolType.Comma)
                    first = false

                    args.add(expression(variables))
                }
                funcCall(ident,  args)
            } else {
                val variable = variables.find { it.first == ident } ?: throw TokenException(tokens.prevToken.line, tokens.prevToken.pos, "Undefined Variable")
                val offset = variables.indexOf(variable)
                return if (tokens.consumeSymbol(SymbolType.Inc)) {
                    opCall("inc", listOf(Node.Variable(variable.second, offset)))
                } else if (tokens.consumeSymbol(SymbolType.Dec)) {
                    opCall("dec", listOf(Node.Variable(variable.second, offset)))
                } else {
                    Node.Variable(variable.second, offset)
                }
            }
        } else {
            Node.Number(Type.Int, tokens.expectNumber())
        }
    }

    private fun opCall(name: String, args: List<Node>): Node.FnCall {
        // debug 引数を表示
        println("op_${name}, $args")
        return Node.FnCall(functions.find { it.first == "op_${name}" && it.second == args.map { arg -> arg.type } }?.third ?: throw TokenException(tokens.prevToken.line, tokens.prevToken.pos, "Undefined Operator"), "op_${name}", args)
    }

    private fun funcCall(name: String, args: List<Node>): Node.FnCall {
        // debug 引数を表示
        println("fn_${name}, $args")
        return Node.FnCall(functions.find { it.first == "fn_${name}" && it.second == args.map { arg -> arg.type } }?.third ?: throw TokenException(tokens.prevToken.line, tokens.prevToken.pos, "Undefined Function"), "fn_${name}", args)
    }
}