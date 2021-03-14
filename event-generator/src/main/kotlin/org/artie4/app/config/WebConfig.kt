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

        val eventConsumerUrl = "http://localhost:8682"

        val httpClient = HttpClient.create().baseUrl(eventConsumerUrl).wiretap(true)

        val client = WebClient.builder()
            .baseUrl(eventConsumerUrl)
            .defaultCookie("cookieKey", "cookieValue")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .clientConnector(ReactorClientHttpConnector(httpClient))
            .build()

        return client
    }
}