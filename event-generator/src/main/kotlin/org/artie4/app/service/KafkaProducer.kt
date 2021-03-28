package org.artie4.app.service

import mu.KotlinLogging
import org.apache.kafka.clients.producer.ProducerRecord
import org.artie4.app.model.Order
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

private val logger = KotlinLogging.logger { }

@Service
class KafkaProducer constructor(private val kafkaTemplate: KafkaTemplate<Long, Order>) {

    fun produce(goodsOrder: Order) {
        val producerRecord = ProducerRecord("orders", goodsOrder.id, goodsOrder)
        try {
            val send = kafkaTemplate.send(producerRecord)
            logger.info { "$producerRecord" }
        } catch (ex: Exception) {
            logger.error("${ex.message} ${ex.suppressed}")
        }
    }
}
