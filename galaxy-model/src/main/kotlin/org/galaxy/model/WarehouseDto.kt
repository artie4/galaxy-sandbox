package org.galaxy.model


data class WarehouseDto(
    val planetId: Int,
    val planetName: String,
    val products: List<ProductDto>
)