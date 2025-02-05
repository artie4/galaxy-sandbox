package org.artie4.datatier.service

import io.github.oshai.kotlinlogging.KotlinLogging
import org.galaxy.model.ProductDto
import org.galaxy.model.Products
import org.galaxy.model.WarehouseDto
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Service
import java.math.BigDecimal

private val logger = KotlinLogging.logger { }

@Service
class WarehouseDaoService(
    private val namedParameterJdbcTemplate: NamedParameterJdbcTemplate
) {

    suspend fun getWarehouseByPlanetId(planetId: Int): WarehouseDto =
        namedParameterJdbcTemplate.query(
            """
        SELECT 
            p.id AS planet_id, 
            p.name AS planet_name, 
            p2p.product_type, 
            p2p.amount, 
            p2p.price 
        FROM planet p 
        LEFT JOIN planet2product p2p ON p.id = p2p.planet_id 
        WHERE p.id = :planet_id
        """,
            mapOf("planet_id" to planetId),
            RowMapper { rs, _ ->
                Pair(
                    rs.getInt("planet_id") to rs.getString("planet_name"),
                    rs.getString("product_type")?.let {
                        ProductDto(
                            productType = Products.valueOf(it),
                            amount = rs.getInt("amount"),
                            price = rs.getBigDecimal("price")
                        )
                    }
                )
            }
        ).let { rows ->
            val (planet, products) = rows.groupBy({ it.first }, { it.second })
                .entries.first() // Since we fetch by planetId, there's only one entry
            WarehouseDto(
                planetId = planet.first,
                planetName = planet.second,
                products = products.filterNotNull().ifEmpty {
                    Products.entries.map { ProductDto(productType = it, amount = 0, price = BigDecimal.ONE) }
                }
            )
        }
}
