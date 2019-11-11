package com.example.jwttest.core.security

import java.security.PrivateKey
import java.security.PublicKey
import java.security.cert.Certificate

data class KeyContainer(
        val privateKey: PrivateKey,
        val publicKey: PublicKey,
        val certificate: Certificate)
//        val Base64Cert: String)