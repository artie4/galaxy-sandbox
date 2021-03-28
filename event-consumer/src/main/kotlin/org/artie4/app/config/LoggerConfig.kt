package org.artie4.app.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class LoggerConfig {

    @Bean
    fun logger(): Logger =
        LoggerFactory.getLogger(LoggerConfig::class.java.name)
}
