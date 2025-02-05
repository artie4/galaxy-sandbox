package org.artie4.datatier.repository

import org.artie4.datatier.entity.Planet
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface PlanetRepository : JpaRepository<Planet, Long> {

    @Query("select p from Planet p left join fetch p.products")
    fun findAllWithProducts(): List<Planet>

    @Query("select p from Planet p left join fetch p.products where p.id = :id")
    fun findByIdWithProducts(@Param("id") id: Long): Planet?
}
