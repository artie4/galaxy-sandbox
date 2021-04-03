package org.galaxy.model

data class Order(
    val id: Long = 0L,
    val productType: Products,
    val amount: Int,
    val destination: String
)
