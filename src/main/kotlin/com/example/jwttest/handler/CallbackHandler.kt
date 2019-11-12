package com.example.jwttest.handler

import com.example.jwttest.api.CallbackResponse
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class CallbackHandler {

    fun sendToNextServer(request: ServerRequest): Mono<ServerResponse> {
        val webClient = WebClient.builder()
                .baseUrl("http://localhost:8055")
                .build()

        val sth = webClient
                .get()
                .uri("/test/applications/get-it")
                .header("Authorization", "Bearer eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJhMGVlYmM5OS05YzBiLTRlZjgtYmI2ZC02YmI5YmQzODBhMTEiLCJyb2xlcyI6WyJST0xFX0NBTExCQUNLIl0sImlhdCI6MTU3MzU5NzQzNCwiZXhwIjoxNTczNjMzNDM0fQ.hCBCoTHbAlMZyNpSVdRfMi4T-iMOTJ1sgrjJ3_u1fMwmBcGsVnw9V7VQODsLg6JfwgVY0g3MtSkaqU2UbedqpXvodiVHciF6OVdRFSxfprCVV3GpRnBdsaD5cUvHXhjk7PDlV4wnp767_quaF6DxFLCAz4G8EbbZB56qK5j_bKGFV5EAchoq7skK_pzzYCHmBFrrI2qG2RBt2hQYnhL2VUitgT0usugqae4jKzZpCK_jeFCaYzxlETXMEsVhKAXLU3-kTVpqewKI8Z0gLeDuJD85eMA0wsEAAt2qn5hs1ozUOpuDCOac1ORBP0LksTeG6sWME-OMK_BAbb2PWcKh7A")
                .retrieve()
                .bodyToMono(CallbackResponse::class.java)
        return ServerResponse.ok().body(sth, CallbackResponse::class.java)

    }
}

//from second server:
// .pathMatchers("/test/applications/get-it").hasRole("CALLBACK")

//@PreAuthorize("hasRole('CALLBACK')")
//    fun getCallbackResponse(request: ServerRequest): Mono<ServerResponse> {
//        val response = Mono.just(CallbackResponse(body = "JESTEM W SERWISIE SECOND"))
//        logger.info("I was here")
//        return ServerResponse.ok().body(response, CallbackResponse::class.java)
//    }


//.................................................................................
//        return webClient
//                .get()
//                .uri("/test/applications/get-it")
//                .retrieve()
//                .bodyToMono(CallbackResponse::class.java)
//                .publish {
//                    ServerResponse.ok().body(it, CallbackResponse::class.java)
//                }
//                .flatMap {
//                    ServerResponse.ok().body(it, CallbackResponse::class.java)
//                }