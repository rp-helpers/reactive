package com.example.jwttest.controller

import org.springframework.http.MediaType
import org.springframework.http.codec.ServerSentEvent
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.EmitterProcessor
import reactor.core.publisher.Flux
import reactor.core.publisher.FluxSink
import java.time.Duration
import java.time.LocalTime
import java.util.concurrent.ConcurrentHashMap


@RestController
class SseController : EventProcessor() {


    private val emitters = ConcurrentHashMap<String, ServerSentEvent<String>>()
    private val emittersString = ConcurrentHashMap<String, Flux<String>>()
    private val nextTest = ConcurrentHashMap<String, EmitterProcessor<String>>()
    private val nextTestSink = ConcurrentHashMap<String, FluxSink<String>>()

//    private val emitterProcessor = EmitterProcessor<ServerSentEvent<String>>()

//    var processor: EmitterProcessor<String> = EmitterProcessor.create<String>()
//    val sink: FluxSink<String> = processor.sink()


    @GetMapping("/stream-flux", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun streamFlux(): Flux<String> {
        /*Flux
                .interval(Duration.ofSeconds(5))
                .map { sequence ->
                    "Flux - " + LocalTime.now().toString()
                }
        var processor: EmitterProcessor<String> = EmitterProcessor.create<String>()
        val sink: FluxSink<String> = processor.sink()
        nextTestSink.put("test", sink)
        sink.next("test nowy")*/
        return getFlux("one")
    }

    @PostMapping("/resendEasy")
    fun useEmitterString(@RequestBody text: String) {
        val sse = emittersString["test"]


    }


    @GetMapping("/stream-sse")
    fun streamEvents(): Flux<ServerSentEvent<String>> {
        val sseFlux = Flux
                .interval(Duration.ofSeconds(5))
                .map { sequence ->
                    val sse =
                            ServerSentEvent.builder<String>()
                                    .id(sequence.toString())
                                    .event("periodic-event")
                                    .data("SSE - " + LocalTime.now().toString())
                                    .build()
                    emitters.put("test", sse)
                    sse
                }

        return sseFlux
    }

    @PostMapping("/resend")
    fun useEmitter(@RequestBody text: String) {
        val sse = emitters["test"]

    }

    /*@GetMapping("/stream-flux", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun streamFlux(): Flux<String> = Flux
            .interval(Duration.ofSeconds(5))
            .map { sequence ->
                "Flux - " + LocalTime.now().toString()
            }*/

    /*@GetMapping("/stream-sse")
    fun streamEvents(): Flux<ServerSentEvent<String>> = Flux
            .interval(Duration.ofSeconds(5))
            .map { sequence ->
                ServerSentEvent.builder<String>()
                        .id(sequence.toString())
                        .event("periodic-event")
                        .data("SSE - " + LocalTime.now().toString())
                        .build()
            }*/
}

//https://stackoverflow.com/questions/51370463/spring-webflux-flux-how-to-publish-dynamically
//https://projectreactor.io/docs/core/release/reference/#processors
//https://octoperf.com/blog/2019/10/09/kraken-server-sent-events-reactive/
//https://www.programcreek.com/java-api-examples/?api=reactor.core.publisher.EmitterProcessor

//https://stackoverflow.com/questions/48283841/how-to-inform-a-flux-that-i-have-an-item-ready-to-publish

