package org.artie4.datatier.mapper

import org.artie4.datatier.entity.Planet
import org.artie4.datatier.entity.Planet2Product
import org.galaxy.model.PlanetDto
import org.galaxy.model.ProductDto
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface PlanetMapper {

    fun toPlanetDto(source: Planet): PlanetDto

    fun toProductDto(source: Planet2Product): ProductDto
}
