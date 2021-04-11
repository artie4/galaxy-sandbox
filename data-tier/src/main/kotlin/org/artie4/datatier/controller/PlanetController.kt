package org.artie4.datatier.controller

import org.artie4.datatier.entity.Planet
import org.artie4.datatier.repository.PlanetRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RequestMapping("/api/planets")
@RestController
class PlanetController(val planetRepository: PlanetRepository) {

    @GetMapping
    fun getPlanets(): Flux<Planet> {
        return planetRepository.findAll()
    }

}