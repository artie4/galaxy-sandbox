package org.artie4.app.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient

@Configuration
class WebConfig {

    @Bean
    fun getWebClient(): WebClient {

        val httpClient =
            HttpClient
                .create()
                .baseUrl(EVENT_CONSUMER_URL)
                .wiretap(true)

        return WebClient.builder()
            .baseUrl(EVENT_CONSUMER_URL)
            .defaultCookie("cookieKey", "cookieValue")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .clientConnector(ReactorClientHttpConnector(httpClient))
            .build()
    }

    companion object {

        private const val EVENT_CONSUMER_URL = "http://localhost:8682"
    }
}
