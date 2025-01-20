package org.artie4.datatier.service

import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.galaxy.model.ProductDto
import org.galaxy.model.Products
import org.galaxy.model.WarehouseDto
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Service
import java.math.BigDecimal

private val logger = KotlinLogging.logger {  }

@Service
class WarehouseService(
    private val databaseClient: DatabaseClient
) {

    suspend fun getWarehouseByPlanetId(planetId: Int): WarehouseDto =
        databaseClient.sql(
            """
        SELECT 
            p.id AS planet_id, 
            p.name AS planet_name, 
            p2p.product_type, 
            p2p.amount, 
            p2p.price 
        FROM planet p 
        LEFT JOIN planet2product p2p ON p.id = p2p.planet_id 
        WHERE p.id = $1
        """
        )
            .bind(0, planetId)
            .map { row, _ ->
                logger.info { "row: $row" }
                Pair(
                    row.get("planet_id", Int::class.java)!! to row.get("planet_name", String::class.java)!!,
                    ProductDto(
                        productType = Products.valueOf(row.get("product_type", String::class.java)!!),
                        amount = row.get("amount", Int::class.java)!!,
                        price = row.get("price", BigDecimal::class.java)!!
                    )
                )
            }
            .all()
            .collectList()
            .map { rows ->
                logger.info { "After mapping rows: $rows" }
                val (planet, products) = rows.groupBy({ it.first }, { it.second })
                    .entries.first() // Since we fetch by planetId, there's only one entry
                WarehouseDto(
                    planetId = planet.first,
                    planetName = planet.second,
                    products = products.ifEmpty {
                        Products.entries.map { ProductDto(productType = it, amount = 0, price = BigDecimal.ONE) }
                    }
                )
            }.awaitSingleOrNull() ?: throw RuntimeException("Planet not found by id: $planetId")
}