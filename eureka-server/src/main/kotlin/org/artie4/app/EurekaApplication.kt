package org.artie4.app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

@SpringBootApplication
@EnableEurekaServer
class EurekaServerApp

fun main(args: Array<String>) {
    runApplication<EurekaServerApp>(*args)
    println("Eureka server is running")
}
