package io.github.maple8192.maplelang.tokenizer.token.type

/**
 * This class represents a word token type.
 */
enum class WordType(val word: String) {
    Func("fn"),
    Operator("operator"),
    LLVM("__inline_LLVM__"),
    If("if"),
    Else("else"),
    For("for"),
    While("while"),
    Loop("loop"),
    Continue("continue"),
    Break("break"),
    Return("ret"),
    Int("int"),
    Float("flt"),
    Bool("bool"),
}