package org.artie4.app.serdes

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.apache.kafka.common.serialization.Deserializer
import org.artie4.app.model.Order
import org.springframework.stereotype.Component

@Component
class OrderDeserializer : Deserializer<Order> {

    override fun deserialize(topic: String?, data: ByteArray?): Order? =
        try {
            val objectMapper = ObjectMapper().registerKotlinModule()
            objectMapper.readValue(data, Order::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
}
