import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version SpringBoot.VERSION
    id("io.spring.dependency-management") version Spring.DEPENDENCY_MANAGEMENT_VERSION
    kotlin("jvm") version Kotlin.VERSION
    kotlin("plugin.spring") version Kotlin.VERSION
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
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

    // kafka
    implementation("org.springframework.kafka:spring-kafka")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // openapi
    implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:${OpenApi.VERSION}")

    // logs
    implementation("io.github.oshai:kotlin-logging-jvm:${MicroUtilsKotlin.LOGGING_VERSION}")

    implementation(project(":galaxy-model"))

    // tools
    developmentOnly("org.springframework.boot:spring-boot-devtools")

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    // tests
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("io.mockk:mockk:${MockK.VERSION}")
    testImplementation("io.kotest:kotest-runner-junit5:${Kotest.VERSION}")
    testImplementation("io.kotest:kotest-assertions-core:${Kotest.VERSION}")
    testImplementation("io.kotest:kotest-property:${Kotest.VERSION}")

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
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
