package com.light.saber

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SaberApplication

fun main(args: Array<String>) {
    runApplication<SaberApplication>(*args)
}
