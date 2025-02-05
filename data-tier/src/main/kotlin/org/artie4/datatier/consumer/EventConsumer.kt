package org.artie4.datatier.consumer

import com.fasterxml.jackson.databind.ObjectMapper
import io.github.oshai.kotlinlogging.KotlinLogging
import org.artie4.datatier.service.WarehouseService
import org.galaxy.model.ReplenishmentDto
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

private val logger = KotlinLogging.logger { }

@Service
class EventConsumer(
    val objectMapper: ObjectMapper,
    val warehouseService: WarehouseService
) {

    @KafkaListener(topics = ["replenish"], containerFactory = "singleFactory")
    fun consume(replenishmentDto: ReplenishmentDto) {
        logger.info { "consumed = ${objectMapper.writeValueAsString(replenishmentDto)}" }
        warehouseService.replenishPlanetProduct(replenishmentDto)
    }
}
