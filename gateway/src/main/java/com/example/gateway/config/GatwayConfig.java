package com.example.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableHystrix
public class GatwayConfig {

    @Autowired
    AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-service", r -> r.path("/citizen/**")
                        .filters(f -> f.rewritePath("/citizen/(?<s>.*)","/${s}").filter(filter))
                        .uri("http://localhost:8095/"))

                .route("auth-service", r -> r.path("/auth/**")
                        .filters(f -> f.rewritePath("/auth/(?<s>.*)","/${s}").filter(filter))
                        .uri("lb://auth"))
                .route("sms-service", r -> r.path("/sms/**")
                        .filters(f -> f.rewritePath("/sms/(?<s>.*)","/${s}").filter(filter))
                        .uri("lb://ms-notif"))
                .route("demande-service", r -> r.path("/demande/**")
                  .filters(f -> f.rewritePath("/demande/(?<s>.*)","/${s}").filter(filter))
                  .uri("lb://demande"))
                .route("ms-gestion-document", r -> r.path("/document/**")
                        .filters(f -> f.rewritePath("/document/(?<s>.*)","/${s}").filter(filter))
                        .uri("lb://ms-gestion-document"))
                .route("ms-participation-citoyen", r -> r.path("/forum/**")
                        .filters(f -> f.rewritePath("/forum/(?<s>.*)","/${s}").filter(filter))
                        .uri("lb://ms-participation-citoyen"))

                .build();
    }

}
