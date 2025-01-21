package org.artie4.datatier

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.reactive.config.EnableWebFlux

@EnableWebFlux
@SpringBootApplication
class DataTierApplication

fun main(args: Array<String>) {
    runApplication<DataTierApplication>(*args)
}