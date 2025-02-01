package org.artie4.datatier.serdes

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.apache.kafka.common.serialization.Deserializer
import org.galaxy.model.Order
import org.springframework.stereotype.Component

@Component
class ReplenishmentDeserializer : Deserializer<Order> {

    override fun deserialize(topic: String?, data: ByteArray?): Order? =
        try {
            ObjectMapper().registerKotlinModule().readValue(data, Order::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
}
