package org.galaxy.model

data class PlanetDto(
    val id: Int,
    val name: String? = null,
    val system: String? = null,
)