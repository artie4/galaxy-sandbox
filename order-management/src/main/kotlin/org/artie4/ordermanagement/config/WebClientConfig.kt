package org.artie4.ordermanagement.config


import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient


@Configuration
class WebClientConfig {

    @Bean
    fun dataTierWebClient(loadBalancerExchangeFilterFunction: ReactorLoadBalancerExchangeFilterFunction): WebClient =
        WebClient.builder()
            .filter(loadBalancerExchangeFilterFunction)
            .baseUrl("http://data-tier")
            .build()
}