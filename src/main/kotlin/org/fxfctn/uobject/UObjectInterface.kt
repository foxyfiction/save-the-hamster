package org.fxfctn.uobject

interface UObjectInterface {
    operator fun set(key: String, value: Any)
    operator fun get(key: String): Any
    fun remove(key: String)
}