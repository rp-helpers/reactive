package com.example.jwttest.core.security

interface ServerCryptoKeyRepository {
    fun find(keytype: KeyType): KeyContainer
    fun save(keytype: KeyType, keyContainer: KeyContainer)
}