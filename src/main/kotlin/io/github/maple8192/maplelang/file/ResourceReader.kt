package io.github.maple8192.maplelang.file

/**
 * This class reads the resource file.
 */
class ResourceReader(private val name: String) {
    /**
     * Reads the file
     */
    fun read(): List<String> {
        return this.javaClass.classLoader.getResourceAsStream(name)!!.bufferedReader().use { it.readLines() }
    }
}