import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version SpringBoot.VERSION
    id("io.spring.dependency-management") version Spring.DEPENDENCY_MANAGEMENT_VERSION
    id("org.jlleitschuh.gradle.ktlint") version KtLint.VERSION
    kotlin("jvm") version Kotlin.VERSION
    kotlin("plugin.spring") version Kotlin.VERSION
    kotlin("plugin.jpa") version Kotlin.VERSION
    kotlin("kapt") version Kotlin.VERSION
}

group = "org.artie4"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {

    // starters
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    implementation("org.mapstruct:mapstruct:${Mapstruct.VERSION}")
    kapt("org.mapstruct:mapstruct-processor:${Mapstruct.VERSION}")

    implementation("io.mockk:mockk:${MockK.VERSION}")
    implementation("io.kotest:kotest-runner-junit5:${Kotest.VERSION}")
    implementation("io.kotest:kotest-assertions-core:${Kotest.VERSION}")
    implementation("io.kotest:kotest-property:${Kotest.VERSION}")

    // kafka
    implementation("org.springframework.kafka:spring-kafka")

    // database
    implementation("org.postgresql:postgresql:${Database.POSTGRESQL_JDBC_VERSION}")
    implementation("org.flywaydb:flyway-core:${Database.FLYWAY_VERSION}")

    // logs
    implementation("io.github.oshai:kotlin-logging-jvm:${MicroUtilsKotlin.LOGGING_VERSION}")

    implementation(project(":galaxy-model"))

    // tools
    developmentOnly("org.springframework.boot:spring-boot-devtools")

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    // tests
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.testcontainers:testcontainers:${TestContainers.VERSION}")
    testImplementation("org.testcontainers:postgresql:${TestContainers.VERSION}")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.testcontainers:junit-jupiter")
}

dependencyManagement {
    imports {
        mavenBom("org.testcontainers:testcontainers-bom:${TestContainers.VERSION}")
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${SpringCloud.VERSION}")
        mavenBom("org.springframework.boot:spring-boot-dependencies:${SpringBoot.VERSION}")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.named("runKtlintFormatOverMainSourceSet") {
    dependsOn(tasks.named("runKtlintFormatOverKotlinScripts"))
}

tasks.named("runKtlintFormatOverTestSourceSet") {
    dependsOn(tasks.named("runKtlintFormatOverKotlinScripts"))
}
