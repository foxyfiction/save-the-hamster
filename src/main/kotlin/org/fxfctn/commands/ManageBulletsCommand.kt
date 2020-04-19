package org.fxfctn.commands

import org.fxfctn.Configuration
import org.fxfctn.uobject.UObjectInterface

class ManageBulletsCommand(private val gameState: UObjectInterface): CommandInterface {
    override fun execute() {
        val commandsList = mutableListOf<CommandInterface>()
        val bullets = gameState[Configuration.BULLETS_NAME] as MutableList<UObjectInterface>
        val players = gameState[Configuration.PLAYERS_NAME] as MutableList<UObjectInterface>
        val player = players[0]

        for (bullet in bullets) {
            val deltaSpeedX = bullet["deltaSpeedX"] as Int
            val deltaSpeedY = bullet["deltaSpeedY"] as Int
            val moveCommand = MoveCommand(bullet, deltaSpeedX.toFloat(), deltaSpeedY.toFloat());

            commandsList.add(moveCommand)

            val hitPlayerRule = HitPlayerRule(gameState, player, bullet)
            val intersectionRule = IntersectionRule(player, bullet, hitPlayerRule)

            commandsList.add(intersectionRule)

            val destroyBulletCommand = DestroyEntityCommand(gameState, bullet, Configuration.BULLETS_NAME)
            val outOfBoundsRule = OutOfBoundsRule(bullet, Configuration.SCREEN_WIDTH, Configuration.SCREEN_HEIGHT, destroyBulletCommand)
            val isExistRule = IsExistRule(bullet, outOfBoundsRule)
            commandsList.add(isExistRule)
        }

        for (cmd in commandsList) {
            cmd.execute()
        }
    }
}