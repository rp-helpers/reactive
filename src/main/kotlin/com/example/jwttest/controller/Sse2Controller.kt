package com.example.jwttest.controller

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
class Sse2Controller/*(private val processor: MessageProcessor) */ : MessageProcessor() {

    @GetMapping("/receive", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun receive(): Flux<String> {
        val someIndex = "one"
        return Flux.create { fluxSink ->
            register(someIndex, fluxSink)
        }
    }

    @PostMapping("/send")
    fun send(@RequestBody message: String) {
        process("one", message)
    }

    @GetMapping("/get")
    fun getIt(): String {
        return "RESPONSE BODY"
    }


}

//https://github.com/fbeaufume/webflux-sse-sample
