package com.example.jwttest.core.config

import com.example.jwttest.api.AuthResponse
import com.example.jwttest.core.EXPIRATION_TIME
import com.example.jwttest.core.TOKEN_PREFIX
import com.example.jwttest.core.security.KeyType
import com.example.jwttest.core.security.ServerCryptoKeyRepository
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.http.HttpHeaders
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.util.*

@Component
class AuthTokenProviderImpl(private val serverCryptoKeyRepository: ServerCryptoKeyRepository) : AuthTokenProvider {

    private val validityInMiliseconds: Long = EXPIRATION_TIME

    override fun isTokenValid(token: String): Boolean {
        val claims: Claims = getClaims(token)
        return claims.expiration.after(Date())
    }

    override fun getClaims(token: String): Claims {
        val claims: Claims = Jwts.parser()
                .setSigningKey(serverCryptoKeyRepository.find(KeyType.APPLICATION_TYPE).publicKey) // UWAGA na sztywno rodzaj klucza!!!!!! do zmiany jak w przypadku generateAuthToken() - brać z parametru
                .parseClaimsJws(token)
                .body
        return claims
    }

    override fun resolveToken(request: ServerHttpRequest): String? {
        val authHeader = request.headers.getFirst(HttpHeaders.AUTHORIZATION)
        if (authHeader != null && authHeader.startsWith(TOKEN_PREFIX)) {
            return authHeader.substring(7, authHeader.length)
        }
        return null
    }

    override fun generateAuthToken(username: String, roles: List<String>, keyType: KeyType): Mono<AuthResponse> {
        val claims = Jwts.claims().setSubject(username)
        claims.put("roles", roles)
        val currentDate = Date()
        val validity = Date(currentDate.time + validityInMiliseconds)
        val token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(currentDate)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.RS256, serverCryptoKeyRepository.find(keyType).privateKey)
                .compact()
        return Mono.just(AuthResponse(TOKEN_PREFIX.plus(token)))
    }
}