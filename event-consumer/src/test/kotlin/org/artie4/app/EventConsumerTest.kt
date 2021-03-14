package org.artie4.app

import org.artie4.app.config.KafkaConfig
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration

@ActiveProfiles("test")
@EmbeddedKafka
@SpringBootTest(classes = [EventConsumerApp::class])
@ContextConfiguration(classes = [KafkaConfig::class])
class EventConsumerTest {

    @Test
    fun contextLoads() {
    }

}
