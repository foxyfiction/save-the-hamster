package org.fxfctn.commands

import org.fxfctn.uobject.UObjectInterface
import org.fxfctn.Configuration

class FullHealthRule(
    private val target: UObjectInterface,
    private val command: CommandInterface
): CommandInterface {
    override fun execute() {
        val health = target["health"] as Int

        if (health >= Configuration.MAX_PLAYER_HEALTH) {
            command.execute()
        }
    }
}