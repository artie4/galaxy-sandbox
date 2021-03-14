package org.artie4.app

import org.artie4.app.properties.KafkaConsumerProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@EnableConfigurationProperties(value = [KafkaConsumerProperties::class])
@SpringBootApplication
class EventConsumerApp

fun main(args: Array<String>) {
    runApplication<EventConsumerApp>(*args)
    println("Event consumer is running")
}
