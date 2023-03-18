package io.github.maple8192.maplelang.file

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardOpenOption

/**
 * This class writes to the file.
 */
class FileWriter(private val path: Path) {
    /**
     * Writes to the file.
     */
    fun write(list: List<String>) {
        Files.write(path, list, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING)
    }
}