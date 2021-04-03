package org.artie4.app.service

import com.fasterxml.jackson.databind.ObjectMapper
import mu.KotlinLogging
import org.galaxy.model.Order
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

private val logger = KotlinLogging.logger { }

@Service
class EventConsumer(
    val objectMapper: ObjectMapper
) {

    @KafkaListener(topics = ["orders"], containerFactory = "singleFactory")
    fun consume(order: Order) {
        logger.info { "consumed = ${objectMapper.writeValueAsString(order)}" }
    }
}
