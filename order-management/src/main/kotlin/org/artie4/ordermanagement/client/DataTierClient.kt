package org.artie4.ordermanagement.client

import org.artie4.ordermanagement.dto.PlanetDto
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Component
class DataTierClient(
    private val dataTierWebClient: WebClient
) {

    suspend fun getPlanets(): List<PlanetDto> =
        dataTierWebClient.get()
            .uri("/api/v1/planets")
            .retrieve()
            .awaitBody()
}