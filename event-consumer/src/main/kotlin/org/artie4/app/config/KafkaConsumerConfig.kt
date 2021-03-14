package org.artie4.app.config

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.LongDeserializer
import org.apache.kafka.common.serialization.StringDeserializer
import org.artie4.app.model.Order
import org.artie4.app.properties.KafkaConsumerProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.config.KafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.converter.BatchMessagingMessageConverter
import org.springframework.kafka.support.converter.StringJsonMessageConverter


@Configuration
class KafkaConsumerConfig(val consumerProperties: KafkaConsumerProperties) {

    @Bean
    fun batchFactory(
        consumerFactory: ConsumerFactory<Long, Order>,
        stringJsonMessageConverter: StringJsonMessageConverter
    ): KafkaListenerContainerFactory<*> {
        val factory: ConcurrentKafkaListenerContainerFactory<Long, Order> =
            ConcurrentKafkaListenerContainerFactory<Long, Order>()
        factory.consumerFactory = consumerFactory
        factory.isBatchListener = true
        factory.setMessageConverter(BatchMessagingMessageConverter(stringJsonMessageConverter))
        return factory
    }

    @Bean
    fun singleFactory(consumerFactory: ConsumerFactory<Long, Order>): KafkaListenerContainerFactory<*> {
        val factory: ConcurrentKafkaListenerContainerFactory<Long, Order> =
            ConcurrentKafkaListenerContainerFactory<Long, Order>()
        factory.consumerFactory = consumerFactory
        factory.isBatchListener = false
        factory.setMessageConverter(StringJsonMessageConverter())
        return factory
    }

    @Bean
    fun consumerFactory(consumerConfigs: Map<String, Any?>): ConsumerFactory<Long, Order> {
        return DefaultKafkaConsumerFactory(consumerConfigs)
    }

    @Bean
    fun kafkaListenerContainerFactory(): KafkaListenerContainerFactory<*> {
        return ConcurrentKafkaListenerContainerFactory<Any, Any>()
    }

    @Bean
    fun converter(): StringJsonMessageConverter {
        return StringJsonMessageConverter()
    }

    @Profile("!test")
    @Bean
    fun consumerConfigs(): Map<String, Any?> {
        val props: MutableMap<String, Any?> = HashMap()
        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = consumerProperties.kafkaServer
        props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = LongDeserializer::class.java
        props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        props[ConsumerConfig.GROUP_ID_CONFIG] = consumerProperties.kafkaGroupId
        props[ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG] = true
        return props
    }
}