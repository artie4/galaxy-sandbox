package org.artie4.datatier.service.impl

import org.artie4.datatier.mapper.PlanetMapper
import org.artie4.datatier.repository.PlanetRepository
import org.artie4.datatier.service.PlanetService
import org.galaxy.model.PlanetDto
import org.springframework.stereotype.Service

@Service
class PlanetServiceImpl(
    private val planetRepository: PlanetRepository,
    private val planetMapper: PlanetMapper,
) : PlanetService {
    override fun getPlanets(): List<PlanetDto>  =
        planetRepository.findAllWithProducts().map(planetMapper::toPlanetDto)

}