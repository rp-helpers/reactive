package com.example.jwttest.core.security

import java.security.PrivateKey
import java.security.cert.Certificate

interface KeystoreService {

    fun getPrivateKeyFromKeystore(keystorePass: String, keystoreAlias: String, keystorePath: String): PrivateKey

    fun getCertificateFromKeystore(keystorePass: String, keystoreAlias: String, keystorePath: String): Certificate
}