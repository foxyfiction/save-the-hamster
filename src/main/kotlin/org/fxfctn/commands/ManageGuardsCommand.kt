package org.fxfctn.commands

import org.fxfctn.Configuration
import org.fxfctn.uobject.UObjectInterface
import kotlin.random.Random

class ManageGuardsCommand(private val gameState: UObjectInterface): CommandInterface {
    override fun execute() {
        val guards = gameState[Configuration.GUARDS_NAME] as MutableList<UObjectInterface>
        val players = gameState[Configuration.PLAYERS_NAME] as MutableList<UObjectInterface>
        val player = players[0]
        val playerX = player["x"] as Float
        val playerY = player["y"] as Float
        val commandsList = mutableListOf<CommandInterface>()
        val threshold = gameState["threshold"] as Float

        for (guard in guards) {
            val probability = Random.nextInt(0, 100)
            val guardX = guard["x"] as Float
            val guardY = guard["y"] as Float
            val guardSpeed = guard["speed"] as Float

            val xDirection = if (playerX > guardX) 1 else -1
            val yDirection = if (playerY > guardY) 1 else -1

            if (probability > threshold) {
                commandsList.add(FireBulletCommand(gameState, guard, xDirection, yDirection))
            }

            val deltaX = xDirection * guardSpeed
            val deltaY = yDirection * guardSpeed

            commandsList.add(MoveCommand(guard, deltaX, deltaY))
            val revertMoveCommand = MoveCommand(guard,-1 * deltaX,-1 * deltaY)
            val outOfBoundRule = OutOfBoundsRule(
                guard,
                Configuration.PLAYGROUND_OFFSET_X,
                Configuration.PLAYGROUND_OFFSET_Y,
                Configuration.PLAYGROUND_WIDTH + Configuration.PLAYGROUND_OFFSET_X,
                Configuration.PLAYGROUND_HEIGHT + Configuration.PLAYGROUND_OFFSET_Y,
                revertMoveCommand
            )
            commandsList.add(outOfBoundRule)
            commandsList.add(IntersectionRule(player, guard, revertMoveCommand))

            for (otherGuard in guards) {
                if (otherGuard != guard) {
                    val revertNearMoveCommand = MoveCommand(guard,-1 * deltaX,-1 * deltaY)
                    commandsList.add(IntersectionRule(otherGuard, guard, revertNearMoveCommand))
                }
            }
        }

        for (cmd in commandsList) {
            cmd.execute()
        }
    }
}