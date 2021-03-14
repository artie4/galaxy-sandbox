package org.artie4.app.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("kafka.consumer")
data class KafkaConsumerProperties(val kafkaServer: String, val kafkaGroupId: String)