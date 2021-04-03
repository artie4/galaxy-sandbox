package org.artie4.app.config;

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.LongDeserializer
import org.apache.kafka.common.serialization.LongSerializer
import org.artie4.app.serdes.OrderDeserializer
import org.artie4.app.serdes.OrderSerializer
import org.galaxy.model.Order
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.test.EmbeddedKafkaBroker
import org.springframework.kafka.test.utils.KafkaTestUtils

@Primary
@Configuration
class KafkaConfig {

	@Autowired
	lateinit var embeddedKafkaBroker: EmbeddedKafkaBroker

	@Bean
	fun producerFactory(): ProducerFactory<Long, Order> {
		return DefaultKafkaProducerFactory(producerProps())
	}

	@Bean
	fun consumerFactory(): ConsumerFactory<Long, Order> {
		return DefaultKafkaConsumerFactory(consumerProps())
	}

	@Bean
	fun kafkaTemplate(): KafkaTemplate<Long, Order> {
		return KafkaTemplate(producerFactory());
	}

	private fun producerProps(): Map<String, Any> {
		val props: MutableMap<String, Any> = KafkaTestUtils.producerProps(embeddedKafkaBroker)
		props[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = LongSerializer::class.java
		props[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = OrderSerializer::class.java
		props[ProducerConfig.LINGER_MS_CONFIG] = 10
		return props
	}

	private fun consumerProps(): Map<String, Any> {
		val consumerProps = KafkaTestUtils.consumerProps("consumer", "false", embeddedKafkaBroker)
		consumerProps[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = LongDeserializer::class.java
		consumerProps[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = OrderDeserializer::class.java
		consumerProps[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = "earliest"
		return consumerProps

	}

}
