package org.galaxy.model

import java.io.Serializable

data class ReplenishmentDto(
    val amount: Int,
    val productType: Products,
    val destination: ReplenishmentDestDto
): Serializable

data class ReplenishmentDestDto(
    val planetId: Long
): Serializable
