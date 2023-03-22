package io.github.maple8192.maplelang.parser

import io.github.maple8192.maplelang.exception.TokenException
import io.github.maple8192.maplelang.parser.node.type.Type
import io.github.maple8192.maplelang.tokenizer.token.Token
import io.github.maple8192.maplelang.tokenizer.token.type.SymbolType
import io.github.maple8192.maplelang.tokenizer.token.type.WordType
import io.github.maple8192.maplelang.util.Either

/**
 * This class represents token queue.
 */
class TokenQueue(private val tokenList: List<Token>) {
    private var index = 0

    val currentToken: Token
        get() = tokenList[index]
    val prevToken: Token
        get() = if (index != 0) tokenList[index - 1] else Token.Eof(0, 0)

    fun consumeSymbol(type: SymbolType): Boolean {
        val token = currentToken
        if (token is Token.Symbol) {
            if (token.type == type) {
                index++
                return true
            }
        }
        return false
    }

    fun consumeWord(type: WordType): Boolean {
        val token = currentToken
        if (token is Token.Word) {
            if (token.type == type) {
                index++
                return true
            }
        }
        return false
    }

    fun consumeType(): Type? {
        val token = currentToken
        if (token is Token.Word) {
            val type = when (token.type) {
                WordType.Int -> Type.Int
                WordType.Float -> Type.Float
                WordType.Bool -> Type.Bool
                else -> return null
            }
            index++
            return type
        }
        return null
    }

    fun expectSymbol(type: SymbolType) {
        val token = currentToken
        if (token is Token.Symbol) {
            if (token.type == type) {
                index++
                return
            }
        }
        throw TokenException(currentToken.line, currentToken.pos, "${type.symbol} expected.")
    }

    fun expectWord(type: WordType) {
        val token = currentToken
        if (token is Token.Word) {
            if (token.type == type) {
                index++
                return
            }
        }
        throw TokenException(currentToken.line, currentToken.pos, "${type.word} expected.")
    }

    fun expectIdent(): String {
        val token = currentToken
        if (token is Token.Ident) {
            index++
            return token.ident
        }
        throw TokenException(currentToken.line, currentToken.pos, "Ident token expected.")
    }

    fun expectNumber(): Either<Long, Double> {
        val token = currentToken
        if (token is Token.IntNum) {
            index++
            return Either.Left(token.num)
        } else if (token is Token.FltNum) {
            index++
            return Either.Right(token.num)
        }
        throw TokenException(currentToken.line, currentToken.pos, "Number token expected.")
    }

    fun expectString(): String {
        val token = currentToken
        if (token is Token.Str) {
            index++
            return token.str
        }
        throw TokenException(currentToken.line, currentToken.pos, "String token expected.")
    }

    fun expectType(): Type {
        val token = currentToken
        if (token is Token.Word) {
            val type = when (token.type) {
                WordType.Int -> Type.Int
                WordType.Float -> Type.Float
                WordType.Bool -> Type.Bool
                else -> throw TokenException(currentToken.line, currentToken.pos, "Type token expected.")
            }
            index++
            return type
        }
        throw TokenException(currentToken.line, currentToken.pos, "Type token expected.")
    }
}