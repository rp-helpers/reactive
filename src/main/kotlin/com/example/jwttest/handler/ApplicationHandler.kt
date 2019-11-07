package com.example.jwttest.handler

import com.example.jwttest.api.ApplicationRequest
import com.example.jwttest.api.ApplicationResponse
import com.example.jwttest.domain.Application
import com.example.jwttest.repository.ApplicationRepository
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserter
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import reactor.core.publisher.Mono
import java.util.*

@Component
class ApplicationHandler(private val applicationRepository: ApplicationRepository) {

    fun save(request: ServerRequest): Mono<ServerResponse> {
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

    }
}