package org.artie4.datatier.service.impl

import org.artie4.datatier.dto.PriceCalculationDto
import org.artie4.datatier.service.PriceCalculationService
import org.galaxy.model.Products
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class PriceCalculationServiceImpl() : PriceCalculationService {

    override fun calculate(calculationDto: PriceCalculationDto): BigDecimal {
        val basePrice = PRODUCT_TO_BASE_PRICE[calculationDto.productType]
            ?: throw IllegalArgumentException("Base price for product type: ${calculationDto.productType} not found")

        val amountRate = PRODUCT_TO_AMOUNT_PRICE_RATE[calculationDto.productType]
            ?.entries
            ?.lastOrNull { it.key < calculationDto.amount }
            ?.value
            ?: throw IllegalArgumentException("Rate for product type: ${calculationDto.productType} and amount: ${calculationDto.amount} not found")

        return calculationDto.planetProductRate.multiply(BigDecimal(basePrice)).multiply(BigDecimal(amountRate))
    }

    // todo move to ext config
    companion object {

        private val foodstuffAmountPriceRate = mapOf(0 to 9.0, 100 to 7.5, 1000 to 2.5, 5000 to 2.0)
        private val medicinesAmountPriceRate = mapOf(0 to 10.0, 100 to 8.0, 1000 to 3.0, 5000 to 2.5)
        private val technicsAmountPriceRate = mapOf(0 to 6.0, 100 to 5.5, 1000 to 2.5, 5000 to 1.5)
        private val mineralsAmountPriceRate = mapOf(0 to 5.0, 100 to 4.0, 1000 to 2.0, 5000 to 1.3)

        private val PRODUCT_TO_AMOUNT_PRICE_RATE = mapOf(
            Products.FOODSTUFF to foodstuffAmountPriceRate,
            Products.MEDICINES to medicinesAmountPriceRate,
            Products.TECHNICS to technicsAmountPriceRate,
            Products.MINERALS to mineralsAmountPriceRate
        )

        private val PRODUCT_TO_BASE_PRICE = mapOf(
            Products.FOODSTUFF to 60,
            Products.MEDICINES to 120,
            Products.TECHNICS to 220,
            Products.MINERALS to 90
        )
    }
}
