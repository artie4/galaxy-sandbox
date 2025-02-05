package org.artie4.datatier.service.impl

import io.github.oshai.kotlinlogging.KotlinLogging
import org.artie4.datatier.dto.PriceCalculationDto
import org.artie4.datatier.mapper.PlanetMapper
import org.artie4.datatier.repository.PlanetRepository
import org.artie4.datatier.service.PriceCalculationService
import org.artie4.datatier.service.WarehouseService
import org.galaxy.model.PlanetDto
import org.galaxy.model.ReplenishmentDto
import org.springframework.stereotype.Service

private val logger = KotlinLogging.logger { }

@Service
class WarehouseServiceImpl(
    private val planetRepository: PlanetRepository,
    private val priceCalculationService: PriceCalculationService,
    private val planetMapper: PlanetMapper
) : WarehouseService {

    override fun replenishPlanetProduct(replenishmentDto: ReplenishmentDto): PlanetDto? {
        val planet = planetRepository.findByIdWithProducts(replenishmentDto.destination.planetId)
            ?: throw IllegalArgumentException("Planet not found by planetId: ${replenishmentDto.destination.planetId}")

        return planet.apply {
            products.first { it.productType == replenishmentDto.productType }.apply {
                this.amount += replenishmentDto.amount
                this.price = priceCalculationService.calculate(
                    PriceCalculationDto(
                        productType = replenishmentDto.productType,
                        amount = this.amount,
                        planetProductRate = this.rate
                    )
                )
            }
        }.let { planetRepository.save(it) }
            .let { planetMapper.toPlanetDto(it) }
    }
}
