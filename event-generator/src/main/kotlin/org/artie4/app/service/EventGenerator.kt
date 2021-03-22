package org.artie4.app.service

import mu.KotlinLogging
import org.artie4.app.model.Order
import org.artie4.app.model.Products
import org.artie4.app.properties.GeneratorProperties
import org.springframework.stereotype.Service
import reactor.kotlin.core.publisher.toFlux
import java.util.concurrent.ThreadLocalRandom
import java.util.concurrent.TimeUnit
import java.util.stream.Stream
import javax.annotation.PostConstruct

private val logger = KotlinLogging.logger { }

@Service
class EventGenerator constructor(
    private val kafkaProducer: KafkaProducer,
    private val generatorProperties: GeneratorProperties
) {

    @PostConstruct
    fun init() {
        generate()
    }

    var counter = 0L

    fun generate() {
        logger.info("[RequestGenerator.generate] start generation")
        try {
            Stream.generate({ randomCity() })
                .limit(generatorProperties.eventNumber)
                .map {
                    TimeUnit.MILLISECONDS.sleep(generatorProperties.delayInMs)
                    TimeUnit.MILLISECONDS.sleep(200)
                    ++counter
                    Order(counter, randomProduct(), randomAmount(), "city$it")
                }
                .peek {
                    logger.info { "[RequestGenerator.generate] send request ${it.id}" }
                    kafkaProducer.produce(it)
                }
                .toFlux().subscribe()
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
}
