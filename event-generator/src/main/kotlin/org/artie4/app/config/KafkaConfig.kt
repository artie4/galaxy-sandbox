package org.artie4.app.config

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.LongSerializer
import org.artie4.app.model.Order
import org.artie4.app.serdes.OrderSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory


@Configuration
class KafkaConfig {

    @Bean
    fun producerFactory(): ProducerFactory<Long, Order> {
        return DefaultKafkaProducerFactory(producerProps())
    }

    fun producerProps(): Map<String, Any> {
        val props = mutableMapOf<String, Any>()
        props[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = "localhost:9092"
        props[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = LongSerializer::class.java
        props[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = OrderSerializer::class.java
        props[ProducerConfig.LINGER_MS_CONFIG] = 10
        return props
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<Long, Order> {
        return KafkaTemplate(producerFactory());
    }
}
