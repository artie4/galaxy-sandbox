package org.artie4.datatier

import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.utility.DockerImageName

class PostgreSQLContainerInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {

    override fun initialize(applicationContext: ConfigurableApplicationContext) {
        PostgreSQLContainer<Nothing>(
            DockerImageName.parse("docker.io/library/postgres:13.2-alpine")
                .asCompatibleSubstituteFor("postgres")
        ).apply {
            start()
            TestPropertyValues
                .of("spring.datasource.url=$jdbcUrl")
                .and("spring.datasource.username=$username")
                .and("spring.datasource.password=$password")
                .and("spring.datasource.driver-class-name=org.postgresql.Driver")
                .applyTo(applicationContext.environment)
        }
    }
}
