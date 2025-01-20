package org.artie4.ordermanagement.client

import org.galaxy.model.PlanetDto
import org.galaxy.model.WarehouseDto
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import reactor.core.publisher.Mono

@Component
class DataTierClient(
    private val dataTierWebClient: WebClient
) {

    suspend fun getPlanets(): List<PlanetDto> =
        dataTierWebClient.get()
            .uri("/api/v1/planets")
            .retrieve()
            .awaitBody()

    suspend fun getPlanetWarehouse(planetId: Int): WarehouseDto =
        dataTierWebClient.get()
            .uri("/api/v1/planets/warehouse/$planetId")
            .retrieve()
            .onStatus({ s -> HttpStatus.NOT_FOUND == s}, { Mono.empty() })
            .awaitBody()
}