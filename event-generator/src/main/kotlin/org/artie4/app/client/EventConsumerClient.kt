package org.artie4.app.client

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import java.time.LocalDateTime

private val logger = KotlinLogging.logger {  }

@Service
class EventConsumerClient(
    private val webClient: WebClient
) {

    suspend fun pushEvent(): Mono<String> = runCatching {
            logger.info { "[${LocalDateTime.now()}] [pushEvent]" }

            webClient.post()
                .uri {
                    it
                        .path("/test")
                        .queryParam("message", "Message from Generator")
                        .build()
                }
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(String::class.java)
                .also { logger.info { "[${LocalDateTime.now()}] $it [pushEvent]" } }
        }.onFailure {
            logger.warn { "[${LocalDateTime.now()}] $it.message" }
        }.getOrElse { Mono.empty() }

}
