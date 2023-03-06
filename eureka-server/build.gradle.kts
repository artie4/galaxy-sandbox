import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version 3.0.4
    id("io.spring.dependency-management") version Spring.DEPENDENCY_MANAGEMENT_VERSION
    kotlin("jvm") version Kotlin.VERSION
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
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-server")
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    // tests
    testImplementation("org.springframework.boot:spring-boot-starter-test")

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
