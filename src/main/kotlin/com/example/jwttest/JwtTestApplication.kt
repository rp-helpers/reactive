package com.example.jwttest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@SpringBootApplication
//@EnableR2dbcRepositories
class JwtTestApplication

fun main(args: Array<String>) {
    runApplication<JwtTestApplication>(*args)
}


//https://github.com/spring-projects/spring-security/issues/4790
//https://github.com/spring-projects/spring-security/issues/5248
//https://stackoverflow.com/questions/52650382/spring-security-webflux-how-to-make-serverwebexchange-return-pre-authentication
//https://stackoverflow.com/questions/46140451/how-to-get-securitycontext-in-spring-security-webflux?noredirect=1&lq=1
//https://stackoverflow.com/questions/46793245/how-preauthorize-is-working-in-an-reactive-application-or-how-to-live-without-t
//https://spring.io/blog/2017/11/01/spring-security-5-0-0-rc1-released


//http://localhost:8077/test/applications/get/a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11
//http://localhost:8077/test/applications/save
//{
//	"name":"moja apka 4"
//}

//http://localhost:8077/test/applications/auth
//{
//	"publicApplicationId":"a02a1bba-258d-4d05-a3fc-e44805e793dc"
//}