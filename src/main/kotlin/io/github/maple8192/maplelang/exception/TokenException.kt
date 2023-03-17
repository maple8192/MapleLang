package io.github.maple8192.maplelang.exception

class TokenException(val line: Int, val pos: Int, override val message: String?) : Exception()