package org.fxfctn.commands

import org.fxfctn.Configuration
import org.fxfctn.uobject.UObjectInterface

class HitPlayerRule(
    private val gameState: UObjectInterface,
    private val target: UObjectInterface,
    private val bullet: UObjectInterface
): CommandInterface {
    override fun execute() {
        val damage = bullet["damage"] as Int

        ChangeHealthCommand(target, -damage).execute()
        DestroyEntityCommand(gameState, bullet, Configuration.BULLETS_NAME).execute()

    }
}