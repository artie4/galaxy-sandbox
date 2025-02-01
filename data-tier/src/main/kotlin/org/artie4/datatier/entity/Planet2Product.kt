package org.artie4.datatier.entity

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import org.galaxy.model.Products
import java.math.BigDecimal

@Entity
class Planet2Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    @Enumerated(EnumType.STRING)
    val productType: Products,
    var amount: Int,
    var price: BigDecimal,
    var rate: BigDecimal,
    @ManyToOne(optional = false)
    var planet: Planet
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Planet2Product

        if (amount != other.amount) return false
        if (id != other.id) return false
        if (productType != other.productType) return false
        if (price != other.price) return false
        if (planet.id != other.planet.id) return false

        return true
    }

    override fun hashCode(): Int {
        var result = amount
        result = 31 * result + id
        result = 31 * result + productType.hashCode()
        result = 31 * result + price.hashCode()
        result = 31 * result + planet.id.hashCode()
        return result
    }

    override fun toString(): String {
        return "Planet2Product(id=$id, productType=$productType, amount=$amount, price=$price, planetId=$planet.id)"
    }
}
