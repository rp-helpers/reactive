package com.example.jwttest.controller

import reactor.core.publisher.Flux
import java.time.Duration
import java.util.concurrent.ConcurrentHashMap

open class EventProcessor {

    private val flux = Flux.empty<String>()
    private val map = ConcurrentHashMap<String, Flux<String>>()

    fun getFlux(index: String): Flux<String> {
//        val flux = Flux.empty<String>()
        val flux = Flux.fromArray(arrayOf("t1", "t2", "t3", "t4", "t5"))
        val fluxResult = Flux.interval(Duration.ofMinutes(1))
                .map { "ping" }
        map[index] = fluxResult
        return fluxResult
    }


}

//https://stackoverflow.com/questions/48283841/how-to-inform-a-flux-that-i-have-an-item-ready-to-publish
//..............................................................
//https://stackabuse.com/spring-reactor-tutorial/
//https://github.com/fbeaufume/webflux-sse-sample
//..............................................................


//https://craftsmen.nl/streaming-data-with-spring-and-eventsource/