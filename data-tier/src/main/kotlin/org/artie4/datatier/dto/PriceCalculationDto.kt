package org.artie4.datatier.dto

import org.galaxy.model.Products
import java.math.BigDecimal

data class PriceCalculationDto(
    val planetProductRate: BigDecimal,
    val productType: Products,
    val amount: Int
)
