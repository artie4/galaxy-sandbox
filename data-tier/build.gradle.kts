import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version SpringBoot.VERSION
    id("io.spring.dependency-management") version Spring.DEPENDENCY_MANAGEMENT_VERSION
    kotlin("jvm") version 1.8.10
    kotlin("plugin.spring") version Kotlin.VERSION
}

group = "org.artie4"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {

    // starters
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // database
    implementation("org.postgresql:postgresql:${Database.POSTGRESQL_JDBC_VERSION}")
    implementation("io.r2dbc:r2dbc-postgresql:${Database.POSTGRESQL_R2DBC_VERSION}")
    implementation("org.flywaydb:flyway-core:${Database.FLYWAY_VERSION}")

    // logs
    implementation("io.github.microutils:kotlin-logging:${MicroUtilsKotlin.LOGGING_VERSION}")

    implementation(project(":galaxy-model"))

    // tools
    developmentOnly("org.springframework.boot:spring-boot-devtools")

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    // tests
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.testcontainers:junit-jupiter")

}

dependencyManagement {
    imports {
        mavenBom("org.testcontainers:testcontainers-bom:${TestContainers.VERSION}")
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${SpringCloud.VERSION}")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
