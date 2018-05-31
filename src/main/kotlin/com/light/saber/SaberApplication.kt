package com.light.saber

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class SaberApplication

fun main(args: Array<String>) {
    runApplication<SaberApplication>(*args)
}

