package io.github.maple8192.maplelang

import io.github.maple8192.maplelang.file.FileReader
import io.github.maple8192.maplelang.exception.TokenException
import io.github.maple8192.maplelang.file.FileWriter
import io.github.maple8192.maplelang.file.ResourceReader
import io.github.maple8192.maplelang.llvm.LLVMGenerator
import io.github.maple8192.maplelang.parser.Parser
import io.github.maple8192.maplelang.tokenizer.Tokenizer
import kotlin.io.path.Path

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

    // デフォルトの関数を読み込んでソースファイルに追加
    val resource = ResourceReader("default.mpl")
    val addedSrc = listOf(resource.read(), src).flatten()

    // トークナイズ
    val tokenizer = Tokenizer(addedSrc)
    val tokens = try {
        tokenizer.tokenize()
    } catch (ex: TokenException) {
        println("Error. (${ex.line + 1}:(${ex.pos + 1})")
        println(addedSrc[ex.line])
        print(" ".repeat(ex.pos))
        println("^ ${ex.message}")
        return
    }

    // 構文解析
    val parser = Parser(tokens)
    val program = try {
        parser.parse()
    } catch (ex: TokenException) {
        println("Syntax Error. (${ex.line + 1}:${ex.pos + 1})")
        println(addedSrc[ex.line])
        print(" ".repeat(ex.pos))
        println("^ ${ex.message}")
        return
    }

    // LLVM-IRを生成
    val generator = LLVMGenerator()
    val llvm = generator.generate(program)

    // ファイルに出力
    val writer = FileWriter(Path(args[1]))
    writer.write(llvm)
}