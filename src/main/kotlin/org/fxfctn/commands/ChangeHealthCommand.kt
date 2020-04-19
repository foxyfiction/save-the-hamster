package org.fxfctn.commands

import org.fxfctn.uobject.UObjectInterface
import org.fxfctn.Configuration

class ChangeHealthCommand(
    private val target: UObjectInterface,
    private val deltaHealth: Int
): CommandInterface {
    override fun execute() {
        val health = target["health"] as Int
        val newHealth = health + deltaHealth

        target["health"] = if (newHealth > Configuration.MAX_PLAYER_HEALTH) Configuration.MAX_PLAYER_HEALTH else newHealth
    }
}