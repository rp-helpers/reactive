package com.example.jwttest.handler

import com.example.jwttest.api.ApplicationResponse
import com.example.jwttest.api.AuthResponse
import com.example.jwttest.core.config.AuthTokenProvider
import com.example.jwttest.core.security.AuthRole
import com.example.jwttest.core.security.KeyType
import com.example.jwttest.repository.ApplicationReactiveRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import java.util.*

@Component
class AuthHandler(private val applicationRepository: ApplicationReactiveRepository,
                  private val tokenProvider: AuthTokenProvider) {

    private val logger = LoggerFactory.getLogger(javaClass)

    fun getAuth(request: ServerRequest): Mono<ServerResponse> {
        logger.info("[AuthHandler]  fun getAuth(request: ServerRequest): Mono<ServerResponse> method:")
        /*  val publicApplicationId = request.bodyToMono(AuthRequest::class.java)
                  .map { authRequest -> authRequest.publicApplicationId }
          val test = request.*/
        val check = request.principal()

        logger.info("[AuthHandler] request.principal() = $check")
        val response = tokenProvider.generateAuthToken("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11", listOf(AuthRole.ROLE_APPLICATION.name), KeyType.APPLICATION_TYPE)
        return ServerResponse.ok().body(response, AuthResponse::class.java)

//        applicationRepository.findByPublicApplicationId(publicApplicationId.)


    }

    fun getAuthRoleUser(request: ServerRequest): Mono<ServerResponse> {
        logger.info("[AuthHandler]  fun getAuth(request: ServerRequest): Mono<ServerResponse> method:")
        /*  val publicApplicationId = request.bodyToMono(AuthRequest::class.java)
                  .map { authRequest -> authRequest.publicApplicationId }
          val test = request.*/
        val check = request.principal()

        logger.info("[AuthHandler] request.principal() = $check")
        val response = tokenProvider.generateAuthToken("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11", listOf(AuthRole.ROLE_USER.name), KeyType.APPLICATION_TYPE)
        return ServerResponse.ok().body(response, AuthResponse::class.java)

//        applicationRepository.findByPublicApplicationId(publicApplicationId.)


    }


    fun save(request: ServerRequest): Mono<ServerResponse> {
//        val application =
//                request
//                        .bodyToMono(ApplicationRequest::class.java)
//                        .map {
//                            ApplicationResponse.from(applicationRepository.save(ApplicationRequest.toApplication(it)))
//                        }
//        return ServerResponse.ok().body(application, ApplicationResponse::class.java)
        return Mono.empty()

    }

    fun get(request: ServerRequest): Mono<ServerResponse> {
        val id: UUID = UUID.fromString(request.pathVariable("id"))
        val application = applicationRepository.findByPublicApplicationId(id)
        val appResponse = application.map {
            ApplicationResponse.from(it)
        }
        return ServerResponse.ok().body(BodyInserters.fromProducer(appResponse, ApplicationResponse::class.java))

    }
}

/*fun save(request: ServerRequest): Mono<ServerResponse> {
        val application =
                request
                        .bodyToMono(ApplicationRequest::class.java)
                        .map {
                            ApplicationResponse.from(applicationRepository.save(ApplicationRequest.toApplication(it)))
                        }
        return ServerResponse.ok().body(application, ApplicationResponse::class.java)

    }

    fun get(request: ServerRequest): Mono<ServerResponse> {
        val id: UUID = UUID.fromString(request.pathVariable("id"))
        val application = Mono.just(applicationRepository.findByPublicApplicationId(id).get())
        val appResponse = application.map {
            ApplicationResponse.from(it)
        }
        return ServerResponse.ok().body(BodyInserters.fromProducer(appResponse, ApplicationResponse::class.java))

    }*/
