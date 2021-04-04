package org.artie4.datatier.controller

import org.artie4.datatier.entity.Planet
import org.artie4.datatier.repository.PlanetRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/planets")
@RestController
class PlanetController(@Autowired val planetRepository: PlanetRepository) {

    @GetMapping
    fun getPlanets(): List<Planet> {
        return planetRepository.findAll()
    }

}