package org.artie4.datatier.repository

import org.artie4.datatier.entity.Planet
import org.springframework.data.r2dbc.repository.R2dbcRepository

interface PlanetRepository : R2dbcRepository<Planet, Int>
