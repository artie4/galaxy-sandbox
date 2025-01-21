package org.artie4.datatier.repository

import org.artie4.datatier.entity.Planet
import org.springframework.data.jpa.repository.JpaRepository

interface PlanetRepository : JpaRepository<Planet, Int>
