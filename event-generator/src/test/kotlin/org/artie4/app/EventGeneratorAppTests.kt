package org.artie4.app

import org.artie4.app.client.EventConsumerClient
import org.artie4.app.config.KafkaConfig
import org.artie4.app.model.Order
import org.artie4.app.model.Products
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import java.time.Duration

@ActiveProfiles("test")
@EmbeddedKafka
@SpringBootTest(classes = [EventGeneratorApp::class])
@ContextConfiguration(classes = [KafkaConfig::class])
class EventGeneratorAppTests {

    @Autowired
    lateinit var defaultKafkaConsumerFactory: ConsumerFactory<Long, Order>

    @MockBean
    lateinit var eventConsumerClient: EventConsumerClient

    @MockBean
    lateinit var rabbitTemplate: RabbitTemplate

    @Test
    fun consumeMessage_Successful() {

        val createConsumer = defaultKafkaConsumerFactory.createConsumer()
            .apply { subscribe(listOf("orders")) }

        val consumerRecords = createConsumer.poll(Duration.ofSeconds(1))

        assertEquals(10, consumerRecords.count())
        consumerRecords.forEach {
            Assertions.assertThat(it)
                .matches { record -> record.value().productType in Products.values() }
        }

    }

}
