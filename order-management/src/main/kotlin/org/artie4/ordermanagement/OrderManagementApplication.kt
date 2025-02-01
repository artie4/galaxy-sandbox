package org.artie4.ordermanagement
import org.artie4.ordermanagement.config.properties.KafkaProducerProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication


@EnableConfigurationProperties(value = [KafkaProducerProperties::class])
@SpringBootApplication
class OrderManagementApplication

fun main(args: Array<String>) {
    runApplication<OrderManagementApplication>(*args)
}