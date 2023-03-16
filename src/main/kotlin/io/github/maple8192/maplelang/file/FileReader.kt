package io.github.maple8192.maplelang.file

import java.io.File

/**
 * This class reads the file.
 */
class FileReader(private val file: File) {
    /**
     * Gets the content of the file.
     */
    fun read(): List<String> {
        return file.readLines()
    }

    companion object {
        /**
         * Gets [FileReader] from the path string.
         */
        fun fromPath(path: String): FileReader? {
            val file = File(path)
            return if (file.exists()) FileReader(file) else null
        }
    }
}