package org.fxfctn.commands

import org.fxfctn.uobject.UObjectInterface

class ChangeHealthCommand(
    private val target: UObjectInterface,
    private val deltaHealth: Int
): CommandInterface {
    override fun execute() {
        val health = target["health"] as Int

        target["health"] = health + deltaHealth
    }
}