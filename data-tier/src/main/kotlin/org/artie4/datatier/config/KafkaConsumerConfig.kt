package org.artie4.datatier.config

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.LongDeserializer
import org.apache.kafka.common.serialization.StringDeserializer
import org.artie4.datatier.config.properties.KafkaConsumerProperties
import org.galaxy.model.ReplenishmentDto
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
        consumerFactory: ConsumerFactory<Long, ReplenishmentDto>,
        stringJsonMessageConverter: StringJsonMessageConverter
    ): KafkaListenerContainerFactory<*> {
        val factory: ConcurrentKafkaListenerContainerFactory<Long, ReplenishmentDto> =
            ConcurrentKafkaListenerContainerFactory<Long, ReplenishmentDto>()
        factory.consumerFactory = consumerFactory
        factory.isBatchListener = true
        factory.setBatchMessageConverter(BatchMessagingMessageConverter(stringJsonMessageConverter))
        return factory
    }

    @Bean
    fun singleFactory(consumerFactory: ConsumerFactory<Long, ReplenishmentDto>): KafkaListenerContainerFactory<*> =
        ConcurrentKafkaListenerContainerFactory<Long, ReplenishmentDto>().apply {
            this.consumerFactory = consumerFactory
            isBatchListener = false
            setRecordMessageConverter(StringJsonMessageConverter())
        }

    @Bean
    fun consumerFactory(consumerConfigs: Map<String, Any?>): ConsumerFactory<Long, ReplenishmentDto> =
        DefaultKafkaConsumerFactory(consumerConfigs)

    @Bean
    fun kafkaListenerContainerFactory(): KafkaListenerContainerFactory<*> =
        ConcurrentKafkaListenerContainerFactory<Any, Any>()

    @Bean
    fun converter(): StringJsonMessageConverter = StringJsonMessageConverter()

    @Profile("!test")
    @Bean
    fun consumerConfigs(): Map<String, Any?> =
        mutableMapOf<String, Any?>().apply {
            this[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = consumerProperties.kafkaServer
            this[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = LongDeserializer::class.java
            this[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
            this[ConsumerConfig.GROUP_ID_CONFIG] = consumerProperties.kafkaGroupId
            this[ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG] = true
        }
}
