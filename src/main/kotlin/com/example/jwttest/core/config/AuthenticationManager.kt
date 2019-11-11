package com.example.jwttest.core.config

import io.jsonwebtoken.Claims
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class AuthenticationManager(
        private val tokenProvider: AuthTokenProvider
) : ReactiveAuthenticationManager {

    private val logger = LoggerFactory.getLogger(javaClass)

    override fun authenticate(authentication: Authentication): Mono<Authentication> {// zmiana na sztywno bez pytajnika była ...
        logger.info("[AuthenticationManager] fun authenticate(authentication: Authentication): Mono<Authentication> method")
        val token = authentication.credentials.toString()
        logger.info("[AuthenticationManager] token = $token")
//        val checker = tokenProvider.isTokenValid(token)
        if (tokenProvider.isTokenValid(token)) {
            val claims: Claims = tokenProvider.getClaims(token)
            val username = claims.subject
            logger.info("[AuthenticationManager] username from token's Claims: $username")
            val roles: List<String> = claims["roles"] as List<String>
            logger.info("[AuthenticationManager] roles from token's Claims: $roles")
            val authorities = roles
                    .map { role ->
                        SimpleGrantedAuthority(role)
                    }
            val auth: UsernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(username, username, authorities)
            logger.info("[AuthenticationManager] val auth: UsernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(username, username, authorities):")
            logger.info("$auth")
            SecurityContextHolder.getContext().authentication = auth
            return Mono.just(auth)
        } else {
            return Mono.empty()
        }
    }
}