package org.artie4.app.serdes

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.apache.kafka.common.serialization.Serializer
import org.galaxy.model.Order

class OrderSerializer : Serializer<Order> {

    override fun serialize(topic: String?, data: Order?): ByteArray? {
        var retVal: ByteArray? = null
        try {
            val objectMapper = ObjectMapper().registerKotlinModule()
            retVal = objectMapper.writeValueAsString(data).toByteArray()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return retVal

    }
}
