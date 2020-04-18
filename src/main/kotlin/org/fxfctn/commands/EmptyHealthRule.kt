package org.fxfctn.commands

import org.fxfctn.uobject.UObjectInterface

class EmptyHealthRule(
    private val target: UObjectInterface,
    private val command: CommandInterface
): CommandInterface {
    override fun execute() {
        val health = target["health"] as Int

        if (health <= 0) {
            command.execute()
        }
    }
}