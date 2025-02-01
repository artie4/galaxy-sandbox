package org.artie4.datatier.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("kafka.consumer")
data class KafkaConsumerProperties(
    val kafkaServer: String,
    val kafkaGroupId: String
)
