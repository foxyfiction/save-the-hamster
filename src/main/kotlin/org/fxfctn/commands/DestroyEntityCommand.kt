package org.fxfctn.commands

import org.fxfctn.uobject.UObjectInterface

class DestroyEntityCommand(
    private val gameState: UObjectInterface,
    private val target: UObjectInterface,
    private val name: String
): CommandInterface {
    override fun execute() {
        val entityList = gameState[name] as MutableList<UObjectInterface>

        entityList.remove(target)
    }
}