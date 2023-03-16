package io.github.maple8192.maplelang.tokenizer.token

/**
 * This class represents a word token type.
 */
enum class WordType(val word: String) {
    Func("fn"),
    If("if"),
    Else("else"),
    For("for"),
    While("while"),
    Loop("loop"),
    Break("break"),
    Return("ret"),
    Int("int"),
    Float("flt"),
}