package com.example.jwttest.controller

import org.slf4j.LoggerFactory
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CheckerController {

    private val logger = LoggerFactory.getLogger(javaClass)

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/try")
    fun tryIt(/*@AuthenticationPrincipal user: User*/) {
       /* logger.error(user.username.toString())
        logger.error(user.password.toString())
        logger.error(user.authorities.toString())*/
    }


}