package org.artie4.app.client

import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import java.time.LocalDateTime
import java.util.logging.Logger
import javax.annotation.PostConstruct

@Service
class EventConsumerClient constructor(private val webClient: WebClient) {

    private val logger: Logger = Logger.getLogger(EventConsumerClient::class.simpleName)

    @PostConstruct
    fun afterInit() {
        pushEvent().block()
    }

    fun pushEvent(): Mono<String> {

        val message = "Message from Generator"

        return try {
            logger.info("[${LocalDateTime.now()}] [pushEvent]")

            val result: Mono<String> = webClient.post()
                .uri {
                    it
                        .path("/test")
                        .queryParam("message", message)
                        .build()
                }
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .exchangeToMono { resp -> resp.bodyToMono(String::class.java) }
            logger.info("[${LocalDateTime.now()}] $result [pushEvent]")
            result

        } catch (ex: Exception) {
            logger.warning("[${LocalDateTime.now()}] $ex.message")
            Mono.empty()
        }
    }


}