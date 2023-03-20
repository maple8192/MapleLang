package io.github.maple8192.maplelang.parser.node

sealed class Statements {
    data class Statement(val node: Node) : Statements()
    data class Block(val statements: List<Statements>) : Statements()
    data class Return(val node: Node) : Statements()
    data class Break(val node: Node? = null) : Statements()
    data class If(val condition: Node, val trueCase: Statements, val falseCase: Statements?) : Statements()
    data class For(val init: Node?, val condition: Node?, val update: Node?, val statement: Statements) : Statements()
    data class While(val condition: Node, val statement: Statements) : Statements()
    data class Loop(val statement: Statements) : Statements()
}
