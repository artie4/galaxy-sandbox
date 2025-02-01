package org.artie4.datatier.controller

import org.artie4.datatier.entity.Planet
import org.artie4.datatier.repository.PlanetRepository
import org.artie4.datatier.service.PlanetService
import org.artie4.datatier.service.WarehouseDaoService
import org.galaxy.model.PlanetDto
import org.galaxy.model.WarehouseDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/v1/planets")
@RestController
class PlanetController(
    private val planetService: PlanetService,
    private val warehouseDaoService: WarehouseDaoService,
) {

    @GetMapping
    fun getPlanets(): List<PlanetDto> = planetService.getPlanets()

    @GetMapping("warehouse/{planet_id}")
    suspend fun getPlanetWarehouse(@PathVariable("planet_id") planetId: Int): WarehouseDto =
        warehouseDaoService.getWarehouseByPlanetId(planetId)
}
