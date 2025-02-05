package org.artie4.datatier.service

import org.galaxy.model.PlanetDto

interface PlanetService {

    fun getPlanets(): List<PlanetDto>
}