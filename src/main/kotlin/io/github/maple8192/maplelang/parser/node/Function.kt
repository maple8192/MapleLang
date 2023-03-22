package io.github.maple8192.maplelang.parser.node

import io.github.maple8192.maplelang.parser.node.type.Type

sealed class Function {
    abstract val name: String
    abstract val argsNum: Int
    abstract val returnType: Type

    data class Normal(override val name: String, override val argsNum: Int, val variables: List<Type>, override val returnType: Type, val statement: Statements) : Function()
    data class LLVM(override val name: String, override val argsNum: Int, val args: List<Type>, override val returnType: Type, val lines: List<String>) : Function()
}
