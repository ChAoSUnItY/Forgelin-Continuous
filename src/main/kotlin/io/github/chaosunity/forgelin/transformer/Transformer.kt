package io.github.chaosunity.forgelin.transformer

interface Transformer {
    fun getTarget(): String
    
    fun getTragetInternalName(): String =
        getTarget().replace('.', '/')
    
    fun transform(originalClass: ByteArray): ByteArray
}