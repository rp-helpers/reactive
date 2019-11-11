package com.example.jwttest.core.config

import com.example.jwttest.api.AuthResponse
import com.example.jwttest.core.security.KeyType
import io.jsonwebtoken.Claims
import org.springframework.http.server.reactive.ServerHttpRequest
import reactor.core.publisher.Mono

interface AuthTokenProvider {

    fun generateAuthToken(username: String, roles: List<String>, keyType: KeyType): Mono<AuthResponse>
    fun resolveToken(request: ServerHttpRequest): String?
    fun isTokenValid(token: String): Boolean
    fun getClaims(token: String): Claims

}