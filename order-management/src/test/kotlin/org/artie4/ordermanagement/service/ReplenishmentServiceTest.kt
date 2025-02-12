package org.artie4.ordermanagement.service

import io.kotest.assertions.assertSoftly
import io.kotest.matchers.comparables.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import org.apache.kafka.clients.producer.ProducerRecord
import org.galaxy.model.ReplenishmentDto
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import java.util.concurrent.CompletableFuture

class ReplenishmentServiceTest {

    private lateinit var sut: ReplenishmentService

    private lateinit var kafkaTemplate: KafkaTemplate<Long, ReplenishmentDto>


    @BeforeEach
    fun setUp() {
        kafkaTemplate = mockk()
        sut = ReplenishmentService(kafkaTemplate)
    }

    @Test
    fun produce() {

        val replenishmentDto = mockk<ReplenishmentDto>()
        val producerRecordSlot = slot<ProducerRecord<Long, ReplenishmentDto>>()
        val sendResult = mockk<SendResult<Long, ReplenishmentDto>> {
            every { producerRecord } returns mockk()
        }

        every { kafkaTemplate.send(capture(producerRecordSlot)) } returns CompletableFuture.completedFuture(sendResult)

        val result = sut.produce(replenishmentDto)

        assertSoftly {
            result shouldBe true
            with(producerRecordSlot.captured) {
                topic() shouldBe "replenish"
                key() shouldBeGreaterThan 0L
                value() shouldBe replenishmentDto
            }
        }
    }
}