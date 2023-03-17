package io.github.maple8192.maplelang.tokenizer

class TokenizeException(val line: Int, val pos: Int, override val message: String?) : Exception()