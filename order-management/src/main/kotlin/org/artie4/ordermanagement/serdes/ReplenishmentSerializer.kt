package org.artie4.ordermanagement.serdes

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.apache.kafka.common.serialization.Serializer
import org.galaxy.model.ReplenishmentDto


class ReplenishmentSerializer: Serializer<ReplenishmentDto> {

    private val objectMapper = jacksonObjectMapper().registerKotlinModule()

    override fun serialize(topic: String?, data: ReplenishmentDto?): ByteArray? {
        var retVal: ByteArray? = null
        try {
            retVal = objectMapper.writeValueAsString(data).toByteArray()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return retVal
    }
}
