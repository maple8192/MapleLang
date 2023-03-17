package io.github.maple8192.maplelang.util

sealed class Either<out L, out R> {
    class Left<out T>(val left: T) : Either<T, Nothing>()
    class Right<out T>(val right: T) : Either<Nothing, T>()
}
