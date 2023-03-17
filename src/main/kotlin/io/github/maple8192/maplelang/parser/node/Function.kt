package io.github.maple8192.maplelang.parser.node

import io.github.maple8192.maplelang.parser.node.type.Type

data class Function(val name: String, val argsNum: Int, val variables: List<Type>, val returnType: Type, val statement: Statements)
