package com.patika.api_gateway.config;

import com.patika.api_gateway.util.JwtRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    private final JwtRequestFilter jwtRequestFilter;

    private static final String[] AUTH_WHITELIST = {
            "/api/v1/users/login",
            "/api/v1/users/register"
    };

    private static final String[] AUTH_WHITELIST2 = {
            "/api/v1/auth/login","api/v1/auth/register"
    };

    private static final String[] AUTH_WHITELIST_USER = {
            "/api/v1/searches/searchByCityAndDepartureDate",
    };
    private static final String[] AUTH_WHITELIST_ADMIN = {

    };

    public SecurityConfig(JwtRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http){

        http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchange -> exchange
                        .pathMatchers("/api/v1/auth/login","api/v1/auth/register").permitAll()
                        .pathMatchers("/api/v1/journeys").hasRole("ADMIN")
                        .pathMatchers("/api/v1/searches").hasRole("USER")

                        .anyExchange().authenticated())
                .addFilterBefore(jwtRequestFilter, SecurityWebFiltersOrder.AUTHENTICATION);

        return http.build();
    }

}

