package org.artie4.ordermanagement.controller

import org.artie4.ordermanagement.client.DataTierClient
import org.galaxy.model.Products
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

    @RestController
    @RequestMapping("/api/v1")
    class ManagementController(
        private val dataTierClient: DataTierClient
    ) {

        @GetMapping("planets")
        suspend fun getPlanets() = dataTierClient.getPlanets()

        @GetMapping("warehouse/{planet_id}")
        suspend fun getPlanets(@PathVariable("planet_id") planetId: Int) = dataTierClient.getPlanetWarehouse(planetId)

//        @RequestMapping("replenishment")
//        suspend fun replenishment(replenishmentDto: ReplenishmentDto) = dataTierClient.replenishment(replenishmentDto)
//
//        data class ReplenishmentDto(
//            val productType: Products,
//            val amount: Int
//        )
    }