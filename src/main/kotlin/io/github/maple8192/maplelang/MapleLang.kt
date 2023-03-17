package io.github.maple8192.maplelang

import io.github.maple8192.maplelang.file.FileReader
import io.github.maple8192.maplelang.exception.TokenException
import io.github.maple8192.maplelang.tokenizer.Tokenizer

fun main(args: Array<String>) {
    // 引数の数をチェック
    if (args.size < 2) {
        println("Two arguments required")
        return
    }

    // ソースファイルを読み取る
    val reader = FileReader.fromPath(args[0])
    if (reader == null) {
        println("The source file does not exist.")
        return
    }
    val src = reader.read()

    // トークナイズ
    val tokenizer = Tokenizer(src)
    val tokens = try {
        tokenizer.tokenize()
    } catch (ex: TokenException) {
        println(src[ex.line])
        print(" ".repeat(ex.pos))
        println("^ ${ex.message}")
        return
    }

    // debug 結果を表示
    println(tokens)
}