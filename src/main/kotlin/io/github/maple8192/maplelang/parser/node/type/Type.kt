package io.github.maple8192.maplelang.parser.node.type

enum class Type(val str: String) {
    Int("i64"),
    Float("double"),
    Bool("i1"),
    Void("void"),
}