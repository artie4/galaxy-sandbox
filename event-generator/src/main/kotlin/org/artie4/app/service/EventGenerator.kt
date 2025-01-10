package org.artie4.app.service

import io.github.oshai.kotlinlogging.KotlinLogging
import org.artie4.app.properties.GeneratorProperties
import org.galaxy.model.Order
import org.galaxy.model.Products
import org.springframework.amqp.core.Message
import org.springframework.amqp.core.MessageProperties
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.util.concurrent.ThreadLocalRandom

private val logger = KotlinLogging.logger { }

@Service
class EventGenerator constructor(
    private val generatorProperties: GeneratorProperties,
    private val kafkaProducer: KafkaProducer,
    private val rabbitTemplate: RabbitTemplate
) {

    @Scheduled(fixedDelayString = "\${generator.delay-in-ms}")
    fun generate() {
        if (counter >= generatorProperties.eventNumber) return
        logger.info("[RequestGenerator.generate] start generation")
        try {
            val randomCity = randomCity()
            ++counter
            val order = Order(counter, randomProduct(), randomAmount(), "city$randomCity")
            logger.info { "[RequestGenerator.generate] send request ${order.id}" }
            kafkaProducer.produce(order)
            rabbitTemplate.send("galaxy.orders", Message(order.toString().toByteArray(), MessageProperties()))
        } catch (ex: Exception) {
            logger.error { ex.message }
        }
        logger.info("[RequestGenerator.generate]  end generation")
    }

    private fun randomCity(): String {
        val cities = listOf("A", "B", "C", "D", "E", "F")
        val nextInt = ThreadLocalRandom.current().nextInt(0, cities.size)
        return cities[nextInt]
    }

    private fun randomProduct(): Products {
        val productArr = Products.values()
        val idx = ThreadLocalRandom.current().nextInt(0, productArr.size)
        return productArr[idx]
    }

    private fun randomAmount(): Int = ThreadLocalRandom.current().nextInt(10, 501)

    companion object {
        var counter = 0L
    }
}
