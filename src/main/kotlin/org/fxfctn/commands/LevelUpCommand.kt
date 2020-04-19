package org.fxfctn.commands

import org.fxfctn.Configuration
import org.fxfctn.uobject.UObjectInterface
import kotlin.math.roundToInt

class LevelUpCommand(
    private val gameState: UObjectInterface,
    private val target: UObjectInterface
): CommandInterface {
    override fun execute() {
        gameState[Configuration.GUARDS_NAME] = mutableListOf<UObjectInterface>()
        gameState[Configuration.BULLETS_NAME] = mutableListOf<UObjectInterface>()
        gameState["isFinished"] = false
        val level = gameState["level"] as Int
        gameState["level"] = level + 1
        val guardSpeed = gameState["guardSpeed"] as Float
        gameState["guardSpeed"] = guardSpeed
        val threshold = gameState["threshold"] as Float
        gameState["threshold"] = threshold * 0.95f
        val increaseHealth = gameState["increaseHealth"] as Int
        gameState["increaseHealth"] = (increaseHealth * 0.95).roundToInt()
        val decreaseHealth = gameState["decreaseHealth"] as Int
        gameState["decreaseHealth"] = (decreaseHealth * 1.05).roundToInt()

        ChangeHealthCommand(target, -Configuration.PLAYER_HEALTH).execute()

        val guardPropsList = listOf<Map<String, Any>>(
            mapOf(
                "x" to (Configuration.PLAYGROUND_WIDTH / 4).toFloat(),
                "y" to (Configuration.PLAYGROUND_HEIGHT / 4).toFloat(),
                "speed" to guardSpeed,
                "radius" to Configuration.PLAYER_RADIUS
            ),
            mapOf(
                "x" to (Configuration.PLAYGROUND_WIDTH / 4 * 3).toFloat(),
                "y" to (Configuration.PLAYGROUND_HEIGHT / 4 * 3).toFloat(),
                "speed" to guardSpeed,
                "radius" to Configuration.PLAYER_RADIUS
            )
        )

        for(guardProps in guardPropsList) {
            CreateEntityCommand(gameState, Configuration.GUARDS_NAME, guardProps).execute()
        }
    }
}