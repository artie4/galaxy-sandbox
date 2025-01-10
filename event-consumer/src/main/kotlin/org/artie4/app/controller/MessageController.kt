package org.artie4.app.controller

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import java.time.LocalDateTime


private val logger = KotlinLogging.logger { }

@RestController
class MessageController {

    @PostMapping("/test")
    fun processMessage(@RequestParam(name = "message") msg: String): Mono<String> {
        logger.info("Message {} is received", msg)
        return Mono.fromSupplier { -> "OK ${LocalDateTime.now()}" }
    }
}
