package org.artie4.datatier.service

import org.galaxy.model.PlanetDto
import org.galaxy.model.ReplenishmentDto

interface WarehouseService {

    fun replenishPlanetProduct(replenishmentDto: ReplenishmentDto): PlanetDto?
}
