package org.artie4.app

import org.artie4.app.client.EventConsumerClient
import org.artie4.app.config.KafkaConfig
import org.assertj.core.api.Assertions
import org.galaxy.model.Order
import org.galaxy.model.Products
import org.junit.jupiter.api.Test
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import java.time.Duration

@ActiveProfiles("test")
@EnableScheduling
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

        var recordCount = 0
        val orderContainer = mutableListOf<Order>()

        while (recordCount < 10) {
            createConsumer.poll(Duration.ofSeconds(1)).also {
                recordCount += it.count()
                val orders = it.map { record -> record.value() }
                orderContainer.addAll(orders)
            }
        }

        orderContainer.forEach {
            Assertions.assertThat(it)
                .matches { order -> order.productType in Products.values() }
        }
    }
}
