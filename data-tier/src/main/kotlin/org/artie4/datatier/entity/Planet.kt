package org.artie4.datatier.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
class Planet(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val name: String? = null,
    val system: String? = null,
    @OneToMany(mappedBy = "planet", cascade = [CascadeType.ALL])
    val products: MutableList<Planet2Product> = mutableListOf()
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Planet

        if (id != other.id) return false
        if (name != other.name) return false
        if (system != other.system) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (system?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "Planet(id=$id, name=$name, system=$system)"
    }
}
