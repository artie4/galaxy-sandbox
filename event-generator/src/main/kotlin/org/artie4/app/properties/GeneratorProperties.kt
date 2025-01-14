package org.artie4.app.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("generator")
data class GeneratorProperties(
    val eventNumber: Long,
    val delayInMs: Long
)
