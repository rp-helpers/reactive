package com.example.jwttest.core.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService
import org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder
import org.springframework.security.config.Customizer.withDefaults
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.context.ServerSecurityContextRepository


@Configuration
@EnableWebFluxSecurity
class SecurityConfig(
        private val authenticationManager: AuthenticationManager,
        private val securityContextRepository: ServerSecurityContextRepository
) {

    @Bean
    fun springSecurityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        http
                .authorizeExchange { exchanges ->
                    exchanges
                            .pathMatchers("/test/applications/get/*").hasRole("USER")
                            .pathMatchers("/test/applications/authUser").permitAll()
                            .pathMatchers("/stream-flux").permitAll()
                            .pathMatchers("/stream-sse").permitAll()
                            .pathMatchers("/receive").permitAll()
                            .pathMatchers("/send").permitAll()
                            .pathMatchers(HttpMethod.POST, "/send").permitAll()
                            .pathMatchers("/get").permitAll()
                            .anyExchange().authenticated()

                }
                .authenticationManager(authenticationManager)
                .securityContextRepository(securityContextRepository)
                .csrf().disable()
        return http.build()
    }


}

//https://docs.spring.io/spring-security/site/docs/current/reference/html5/#jc-webflux

/* @Bean
    fun userDetailsService(): MapReactiveUserDetailsService {
        val user = withDefaultPasswordEncoder()
                .username("user")
                .password("user")
                .roles("USER")
                .build()
        return MapReactiveUserDetailsService(user)
    }*/

/*
@Bean
fun springSecurityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
    http
            .authorizeExchange { exchanges ->
                exchanges
                        .anyExchange().authenticated()
            }
            .httpBasic(withDefaults())
            .formLogin(withDefaults())
    return http.build()
}*/
