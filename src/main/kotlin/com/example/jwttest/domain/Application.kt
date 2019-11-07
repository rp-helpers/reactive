package com.example.jwttest.domain

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "applications")
data class Application(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        @Column(name = "public_application_id")
        val publicApplicationId: UUID,
        @Column(name = "name")
        val name: String,
        @Column(name = "status")
        val status: String,
        @Column(name = "create_date")
        val createDate: Date)