package io.github.maple8192.maplelang.parser.node

import io.github.maple8192.maplelang.parser.node.type.Type

sealed class Node {
    abstract val type: Type

    data class Comma(override val type: Type, val list: List<Node>) : Node()
    data class Assign(override val type: Type, val offset: Int, val node: Node) : Node()
    data class Exch(override val type: Type, val lhsOffset: Int, val rhsOffset: Int) : Node()
    data class Variable(override val type: Type, val offset: Int) : Node()
    data class FnCall(override val type: Type, val funcName: String, val args: List<Node>) : Node()
    data class Number(override val type: Type, val num: String) : Node()
}
