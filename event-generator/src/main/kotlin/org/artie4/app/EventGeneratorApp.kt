package org.artie4.app

import org.artie4.app.properties.GeneratorProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.web.reactive.config.EnableWebFlux

@EnableScheduling
@EnableWebFlux
@SpringBootApplication
@EnableConfigurationProperties(value = [GeneratorProperties::class])
class EventGeneratorApp

fun main(args: Array<String>) {
    runApplication<EventGeneratorApp>(*args)
    println("event-generator is running")
}
