package com.example.jwttest.domain

//import java.util.*
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate
import java.util.*

//import javax.persistence.Table

//import javax.persistence.*

//@Entity
//@Table(name = "applications")
@Table("applications")
data class Application(
        @Id
//        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
//        @Column(name = "public_application_id")
        @Column("public_application_id")
        val publicApplicationId: UUID,
        @Column("name")
        val name: String,
        @Column("status")
        val status: String,
        @Column("create_date")
        val createDate: LocalDate)