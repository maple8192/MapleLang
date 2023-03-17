package io.github.maple8192.maplelang.parser.node

import io.github.maple8192.maplelang.parser.node.type.Type

sealed class Node {
    abstract val type: Type

    sealed class Operator : Node() {
        abstract val lhs: Node
        abstract val rhs: Node

        data class Add(override val type: Type, override val lhs: Node, override val rhs: Node) : Operator()
        data class Sub(override val type: Type, override val lhs: Node, override val rhs: Node) : Operator()
        data class Mul(override val type: Type, override val lhs: Node, override val rhs: Node) : Operator()
        data class Div(override val type: Type, override val lhs: Node, override val rhs: Node) : Operator()
        data class Rem(override val type: Type, override val lhs: Node, override val rhs: Node) : Operator()
        data class Pow(override val type: Type, override val lhs: Node, override val rhs: Node) : Operator()
        data class Root(override val type: Type, override val lhs: Node, override val rhs: Node) : Operator()
        data class And(override val type: Type, override val lhs: Node, override val rhs: Node) : Operator()
        data class Xor(override val type: Type, override val lhs: Node, override val rhs: Node) : Operator()
        data class Or(override val type: Type, override val lhs: Node, override val rhs: Node) : Operator()
        data class LSft(override val type: Type, override val lhs: Node, override val rhs: Node) : Operator()
        data class RSft(override val type: Type, override val lhs: Node, override val rhs: Node) : Operator()
        data class Eq(override val type: Type, override val lhs: Node, override val rhs: Node) : Operator()
        data class Less(override val type: Type, override val lhs: Node, override val rhs: Node) : Operator()
        data class Assign(override val type: Type, override val lhs: Node, override val rhs: Node) : Operator()
        data class ChMin(override val type: Type, override val lhs: Node, override val rhs: Node) : Operator()
        data class ChMax(override val type: Type, override val lhs: Node, override val rhs: Node) : Operator()
        data class Exch(override val type: Type, override val lhs: Node, override val rhs: Node) : Operator()
    }
    data class Variable(override val type: Type, val offset: Int) : Node()
    data class FnCall(override val type: Type, val funcName: String, val args: List<Node>) : Node()
    data class Number(override val type: Type, val number: Long) : Node()
}
