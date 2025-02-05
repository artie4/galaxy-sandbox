package org.artie4.datatier

import org.artie4.datatier.config.properties.KafkaConsumerProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@EnableConfigurationProperties(value = [KafkaConsumerProperties::class])
@SpringBootApplication
class DataTierApplication

fun main(args: Array<String>) {
    runApplication<DataTierApplication>(*args)
}
