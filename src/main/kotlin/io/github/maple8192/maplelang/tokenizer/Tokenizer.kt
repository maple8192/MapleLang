package io.github.maple8192.maplelang.tokenizer

import io.github.maple8192.maplelang.tokenizer.token.type.SymbolType
import io.github.maple8192.maplelang.tokenizer.token.Token
import io.github.maple8192.maplelang.tokenizer.token.type.WordType
import io.github.maple8192.maplelang.util.Either

/**
 * This class tokenizes the source code.
 */
class Tokenizer(private val src: List<String>) {
    /**
     * Tokenizes the source code.
     */
    @Throws(TokenizeException::class)
    fun tokenize(): List<Token> {
        val tokens = mutableListOf<Token>()

        for (line in src.indices) {
            var pos = 0
            while (pos < src[line].length) {
                when (src[line][pos]) {
                    ' ', '\t' -> pos++
                    in '0'..'9' -> {
                        val (num, length) = createNumberToken(src[line].slice(pos until src[line].length))
                        tokens.add(Token.Number(line, pos, num))
                        pos += length
                    }
                    in SymbolType.symbolChars -> {
                        val symbol = createSymbolToken(src[line].slice(pos until src[line].length)) ?: throw TokenizeException(line, pos, "Undefined Token")
                        tokens.add(Token.Symbol(line, pos, symbol))
                        pos += symbol.symbol.length
                    }
                    else -> {
                        pos += when (val either = createWordToken(src[line].slice(pos until src[line].length))) {
                            is Either.Left -> {
                                tokens.add(Token.Word(line, pos, either.left))
                                either.left.word.length
                            }

                            is Either.Right -> {
                                tokens.add(Token.Ident(line, pos, either.right))
                                either.right.length
                            }
                        }
                    }
                }
            }
        }

        tokens.add(Token.Eof(src.lastIndex, src[src.lastIndex].length))

        return tokens.toList()
    }

    private fun createNumberToken(str: String): Pair<Long, Int> {
        for (i in str.indices) {
            if (str[i] !in '0'..'9') {
                return str.slice(0 until i).toLong() to i
            }
        }
        return str.toLong() to str.length
    }

    private fun createSymbolToken(str: String): SymbolType? {
        return SymbolType.lenOrderList.find { str.startsWith(it.symbol) }
    }

    private fun createWordToken(str: String): Either<WordType, String> {
        for (i in str.indices) {
            if (str[i] in SymbolType.symbolChars || str[i] == ' ' || str[i] == '\t') {
                return Either.Left(WordType.values().find { it.word == str.slice(0 until i) } ?: return Either.Right(str.slice(0 until i)))
            }
        }
        return Either.Left(WordType.values().find { it.word == str } ?: return Either.Right(str))
    }
}