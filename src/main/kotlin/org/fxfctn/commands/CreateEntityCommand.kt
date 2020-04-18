package org.fxfctn.commands

import org.fxfctn.uobject.UObject
import org.fxfctn.uobject.UObjectInterface

class CreateEntityCommand(
    private val game: UObjectInterface,
    private val name: String,
    private val properties: Map<String, Any>
): CommandInterface {
    override fun execute() {
        val entity = UObject()
        val keys = properties.keys

        for (key in keys) {
            entity[key] = this.properties[key]!!
        }

        val entityList = game[name] as MutableList<UObjectInterface>

        entityList.add(entity)
    }
}