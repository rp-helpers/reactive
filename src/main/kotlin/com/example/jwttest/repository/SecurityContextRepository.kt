package com.example.jwttest.repository

import com.example.jwttest.core.config.AuthTokenProvider
import com.example.jwttest.core.config.AuthenticationManager
import org.slf4j.LoggerFactory
import org.springframework.http.server.reactive.ServerHttpRequest
//import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextImpl
import org.springframework.security.web.server.context.ServerSecurityContextRepository
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import java.security.Principal

@Component
class SecurityContextRepository(private val authTokenProvider: AuthTokenProvider,
                                private val authenticationManager: AuthenticationManager) : ServerSecurityContextRepository {

    private val logger = LoggerFactory.getLogger(javaClass)

    override fun save(exchange: ServerWebExchange?, context: SecurityContext?): Mono<Void> {
        return Mono.empty()
    }

    override fun load(exchange: ServerWebExchange): Mono<SecurityContext> { // bez pytajnika - sprawdzić z
        logger.info("[SecurityContextRepository] fun load(exchange: ServerWebExchange): Mono<SecurityContext> method")
        val check = exchange.getPrincipal<Principal>()
        logger.info("exchange.getPrincipal<Principal>() = $check")
        val request: ServerHttpRequest = exchange.request
        val token = authTokenProvider.resolveToken(request)
        if (token != null) {
            val auth: Authentication = UsernamePasswordAuthenticationToken(token, token)
            logger.info("[SecurityContextRepository] val auth: Authentication = UsernamePasswordAuthenticationToken(token, token) is: $auth")
            return authenticationManager.authenticate(auth)
                    .map { authentication ->
                        SecurityContextImpl(authentication)
                    }
        } else {
            return Mono.empty()
        }

    }
}

//{
//    "token": "Bearer eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJhMGVlYmM5OS05YzBiLTRlZjgtYmI2ZC02YmI5YmQzODBhMTEiLCJyb2xlcyI6WyJST0xFX0FQUExJQ0FUSU9OIl0sImlhdCI6MTU3MzQ5NDM0NywiZXhwIjoxNTczNTMwMzQ3fQ.WNTPGdb02wBKmfPln0qp9RkM22mRpWCvhUVvWctpcarGAdWnfYL00ds1zvgnPlHOxm7Np6PbrKOI0HBBs-qrvczrok37A1FSn5saZ_EuM44H7am0MeKBh8fVkn4z_Kba1w0Y5PvYhuabZkxdWPmCuZY7QCRjl1oXuKGO_7J1ctykdFfhR91QZ-co4_hHMnD6H-SxUcPGW4DC8hzfk6ZVTNS2tX3z_pvPowMesw-I8cUllGY5UnFPeieLu1tiUy6CoQ27o_CNK-I6I4A15ekxYUSI455l8rodK-0_w1PqjXk91qyYBSdYfHfYUhBvwsQ8fBLw26RKlz8WozuAMr9g_g"
//}