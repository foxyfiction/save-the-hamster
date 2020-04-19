package org.fxfctn.uobject

private class UObjectException(message: String): Exception(message)

class UObject : UObjectInterface {

    private val map = mutableMapOf<String, Any>()

    override operator fun set(key: String, value: Any) {
        map[key] = value
    }

    override operator fun get(key: String): Any {
        return map.getOrElse(key, { throw UObjectException("Property doesn't exist $key") })
    }

    override fun remove(key: String) {
        if (map.containsKey(key))
            map.remove(key)
        else
            throw UObjectException("Trying to remove nonexistent property $key")
    }
}
