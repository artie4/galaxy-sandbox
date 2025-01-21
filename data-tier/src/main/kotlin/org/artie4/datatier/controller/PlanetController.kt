package org.artie4.datatier.controller

import org.artie4.datatier.entity.Planet
import org.artie4.datatier.repository.PlanetRepository
import org.artie4.datatier.service.WarehouseService
import org.galaxy.model.WarehouseDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RequestMapping("/api/v1/planets")
@RestController
class PlanetController(
    private val planetRepository: PlanetRepository,
    private val warehouseService: WarehouseService
) {

    @GetMapping
    fun getPlanets(): Flux<Planet> {
        return planetRepository.findAll()
    }

    @GetMapping("warehouse/{planet_id}")
    suspend fun getPlanetWarehouse(@PathVariable("planet_id") planetId: Int): WarehouseDto =
        warehouseService.getWarehouseByPlanetId(planetId)

}