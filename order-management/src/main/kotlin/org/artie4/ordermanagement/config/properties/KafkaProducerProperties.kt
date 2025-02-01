package org.artie4.ordermanagement.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("kafka.producer")
data class KafkaProducerProperties(
    val broker: String,
    val groupId: String,
)