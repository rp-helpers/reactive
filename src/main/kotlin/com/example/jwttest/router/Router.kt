package com.example.jwttest.router

import com.example.jwttest.handler.ApplicationHandler
import com.example.jwttest.handler.AuthHandler
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.router

@Component
class Router {

    @Bean
    fun routes(applicationHandler: ApplicationHandler, authHandler: AuthHandler) = router {
        "/test/applications".nest {
            POST("/save", applicationHandler::save)
            GET("/get/{id}", applicationHandler::get)
            GET("/auth", authHandler::getAuth)
            GET("/authUser", authHandler::getAuthRoleUser)
        }
    }
}

