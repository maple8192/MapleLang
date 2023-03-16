plugins {
    kotlin("jvm") version "1.8.0"
    application
}

group = "io.github.maple8192"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("io.github.maple8192.maplelang.MapleLangKt")
}