package org.artie4.ordermanagement.config

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.LongSerializer
import org.artie4.ordermanagement.config.properties.KafkaProducerProperties
import org.artie4.ordermanagement.serdes.ReplenishmentSerializer
import org.galaxy.model.ReplenishmentDto
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory


@Configuration
class KafkaConfig(
    private val kafkaProducerProperties: KafkaProducerProperties
) {

    @Bean
    fun producerFactory(): ProducerFactory<Long, ReplenishmentDto> {
        return DefaultKafkaProducerFactory(producerProps())
    }

    fun producerProps(): Map<String, Any> {
        val props = mutableMapOf<String, Any>()
        props[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = kafkaProducerProperties.broker
        props[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = LongSerializer::class.java
        props[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = ReplenishmentSerializer::class.java
        props[ProducerConfig.LINGER_MS_CONFIG] = 10
        return props
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<Long, ReplenishmentDto> {
        return KafkaTemplate(producerFactory());
    }
}
