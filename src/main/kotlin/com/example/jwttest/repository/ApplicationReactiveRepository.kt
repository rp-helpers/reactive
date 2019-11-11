package com.example.jwttest.repository

import com.example.jwttest.domain.Application
import org.springframework.data.r2dbc.repository.query.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono
import java.util.*

interface ApplicationReactiveRepository : ReactiveCrudRepository<Application, Long> {

    @Query("SELECT * FROM applications WHERE public_application_id = $1")
    fun findByPublicApplicationId(id: UUID): Mono<Application>
}