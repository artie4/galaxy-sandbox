plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "galaxy-sandbox"

include("eureka-server")
include("event-generator")
include("event-consumer")
include("galaxy-model")
include("data-tier")
include("order-management")
