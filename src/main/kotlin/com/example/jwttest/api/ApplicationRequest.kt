package com.example.jwttest.api

import com.example.jwttest.domain.Application
import java.util.*

data class ApplicationRequest(val name: String) {
    companion object {
        fun toApplication(applicationRequest: ApplicationRequest): Application =
                Application(publicApplicationId = UUID.randomUUID(),
                        name = applicationRequest.name,
                        status = "activated",
                        createDate = Date())
    }
}