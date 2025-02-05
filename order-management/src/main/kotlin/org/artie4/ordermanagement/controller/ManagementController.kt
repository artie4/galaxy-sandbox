package org.artie4.ordermanagement.controller

import org.artie4.ordermanagement.client.DataTierClient
import org.artie4.ordermanagement.service.ReplenishmentService
import org.galaxy.model.ReplenishmentDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
    @RequestMapping("/api/v1")
    class ManagementController(
    private val dataTierClient: DataTierClient,
    private val replenishmentService: ReplenishmentService,
    ) {

        @GetMapping("planets")
        suspend fun getPlanets() = dataTierClient.getPlanets()

        @GetMapping("warehouse/{planet_id}")
        suspend fun getPlanets(@PathVariable("planet_id") planetId: Int) = dataTierClient.getPlanetWarehouse(planetId)

        @PostMapping("replenishment")
        suspend fun replenishment(@RequestBody replenishmentDto: ReplenishmentDto) = replenishmentService.produce(replenishmentDto)
    }