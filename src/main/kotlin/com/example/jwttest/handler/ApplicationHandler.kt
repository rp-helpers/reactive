package com.example.jwttest.handler

import com.example.jwttest.api.ApplicationRequest
import com.example.jwttest.api.ApplicationResponse
import com.example.jwttest.core.config.AuthTokenProvider
import com.example.jwttest.domain.Application
import com.example.jwttest.repository.ApplicationReactiveRepository
import org.slf4j.LoggerFactory
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.security.core.context.SecurityContextHolder
//import com.example.jwttest.repository.ApplicationRepository
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserter
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import reactor.core.publisher.Mono
import java.security.Principal
import java.util.*

@Component
class ApplicationHandler(private val applicationRepository: ApplicationReactiveRepository,
                         private val tokenProvider: AuthTokenProvider) {

    private val logger = LoggerFactory.getLogger(javaClass)

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
//        logger.info("[ApplicationHandler]  fun get(request: ServerRequest, principal: Principal): Mono<ServerResponse> method:")
//        logger.info("[ApplicationHandler] principal from method's parameter: $principal")

        logger.info("[ApplicationHandler] fun get(request: ServerRequest): Mono<ServerResponse> method:")
        val check = request.principal()
        val check2: Authentication = SecurityContextHolder.getContext().authentication
        val check3 = SecurityContextHolder.getContext().authentication.principal
        val check4 = ReactiveSecurityContextHolder.getContext() .map { sc -> sc.authentication.principal}
//        val sth = ReactiveSecurityContextHolder.getContext() .map { sc -> sc.authentication.principal}
        logger.info("[ApplicationHandler] SecurityContextHolder.getContext().authentication = $check2")
        logger.info("[ApplicationHandler] SecurityContextHolder.getContext().authentication.principal = $check3")
        logger.info("[ApplicationHandler] ReactiveSecurityContextHolder.getContext() .map { sc -> sc.authentication.principal} = $check4")

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
