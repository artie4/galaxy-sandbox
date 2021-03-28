package org.artie4.app.config;

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.LongDeserializer
import org.apache.kafka.common.serialization.LongSerializer
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.test.EmbeddedKafkaBroker
import org.springframework.kafka.test.utils.KafkaTestUtils

@Configuration
class KafkaConfig {

    @Autowired
    lateinit var embeddedKafkaBroker: EmbeddedKafkaBroker

    fun producerProps(): Map<String, Any> {
        val props: MutableMap<String, Any> = KafkaTestUtils.producerProps(embeddedKafkaBroker)
        props[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = LongSerializer::class.java
        props[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        props[ProducerConfig.LINGER_MS_CONFIG] = 10
        return props
    }

    @Bean
    fun consumerConfigs(): Map<String, Any?> {
        val consumerProps = KafkaTestUtils.consumerProps("consumer", "false", embeddedKafkaBroker)
        consumerProps[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = LongDeserializer::class.java
        consumerProps[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        consumerProps[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = "earliest"
        return consumerProps

    }
}
