package com.example.jwttest.api

import com.example.jwttest.domain.Application
import java.util.*

data class ApplicationResponse(
        val publicApplicationId: UUID,
        val name: String,
        val status: String,
        val createDate: Date) {

    companion object {
        fun from(application: Application) =
                ApplicationResponse(
                        publicApplicationId = application.publicApplicationId,
                        name = application.name,
                        status = application.status,
                        createDate = application.createDate)
    }
}