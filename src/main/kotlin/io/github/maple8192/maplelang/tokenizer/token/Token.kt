package io.github.maple8192.maplelang.tokenizer.token

import io.github.maple8192.maplelang.tokenizer.token.type.SymbolType
import io.github.maple8192.maplelang.tokenizer.token.type.WordType

/**
 * This class represents a token.
 */
sealed class Token {
    abstract val line: Int
    abstract val pos: Int

    class Symbol(override val line: Int, override val pos: Int, val type: SymbolType) : Token()
    class Word(override val line: Int, override val pos: Int, val type: WordType) : Token()
    class Ident(override val line: Int, override val pos: Int, val ident: String) : Token()
    class IntNum(override val line: Int, override val pos: Int, val num: Long) : Token()
    class FltNum(override val line: Int, override val pos: Int, val num: Double) : Token()
    class Eof(override val line: Int, override val pos: Int) : Token()
}
