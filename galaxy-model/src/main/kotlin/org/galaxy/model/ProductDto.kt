package org.galaxy.model

import org.galaxy.model.Products
import java.math.BigDecimal

data class ProductDto(
    val productType: Products,
    val amount: Int,
    val price: BigDecimal
)