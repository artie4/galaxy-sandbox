package org.artie4.datatier.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table
class Planet(

    @Id
    val id: Int = 0,
    val name: String? = null,
    val system: String? = null,
)
