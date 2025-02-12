package org.artie4.ordermanagement.service

import io.github.oshai.kotlinlogging.KotlinLogging
import org.apache.kafka.clients.producer.ProducerRecord
import org.galaxy.model.ReplenishmentDto
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import java.time.Instant

private val logger = KotlinLogging.logger {  }

@Service
class ReplenishmentService(
    private val kafkaTemplate: KafkaTemplate<Long, ReplenishmentDto>
) {

    fun produce(replenishment: ReplenishmentDto): Boolean {
        val producerRecord = ProducerRecord("replenish", Instant.now().nano.toLong(), replenishment)
        return try {
            val send = kafkaTemplate.send(producerRecord)
            logger.info { "$producerRecord" }
            send.join()
            send.isDone
        } catch (ex: Exception) {
            logger.error { "${ex.message} ${ex.suppressed}" }
            false
        }
    }
}