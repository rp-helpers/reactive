package com.example.jwttest.controller

import reactor.core.publisher.FluxSink
import java.util.concurrent.ConcurrentHashMap

//@Service
open class MessageProcessor {

    private val map = ConcurrentHashMap<String, FluxSink<String>>()

    fun register(index: String, sink: FluxSink<String>) {
        map[index] = sink
    }

    fun process(index: String, message: String) {
        val sink = map[index]
        sink?.next(message)
    }

    //https://projectreactor.io/docs/core/release/api/reactor/core/publisher/FluxSink.html

}