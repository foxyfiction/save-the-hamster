package org.fxfctn.commands

import org.fxfctn.Configuration
import org.fxfctn.uobject.UObjectInterface

class InitializeGameCommand(private val gameState: UObjectInterface): CommandInterface {
    override fun execute() {
        gameState[Configuration.PLAYERS_NAME] = mutableListOf<UObjectInterface>()
        gameState[Configuration.GUARDS_NAME] = mutableListOf<UObjectInterface>()
        gameState[Configuration.BULLETS_NAME] = mutableListOf<UObjectInterface>()
        gameState["guardSpeed"] = Configuration.GUARD_SPEED
        gameState["threshold"] = Configuration.THRESHOLD
        gameState["increaseHealth"] = Configuration.PLAYER_HEALTH_DELTA
        gameState["decreaseHealth"] = Configuration.PLAYER_HEALTH_DELTA
        gameState["guardQuantity"] = 2
        gameState["level"] = 0
        gameState["isFinished"] = false

        val playerProps = mapOf(
            "x" to (Configuration.PLAYGROUND_WIDTH / 2).toFloat(),
            "y" to (Configuration.PLAYGROUND_HEIGHT / 2).toFloat(),
            "radius" to Configuration.PLAYER_RADIUS,
            "speed" to Configuration.PLAYER_SPEED,
            "health" to Configuration.PLAYER_HEALTH
        )

        CreateEntityCommand(gameState, Configuration.PLAYERS_NAME, playerProps).execute()

        val guardPropsList = listOf<Map<String, Any>>(
            mapOf(
                "x" to (Configuration.PLAYGROUND_WIDTH / 4).toFloat(),
                "y" to (Configuration.PLAYGROUND_HEIGHT / 4).toFloat(),
                "speed" to Configuration.GUARD_SPEED,
                "radius" to Configuration.PLAYER_RADIUS
            ),
            mapOf(
                "x" to (Configuration.PLAYGROUND_WIDTH / 4 * 3).toFloat(),
                "y" to (Configuration.PLAYGROUND_HEIGHT / 4 * 3).toFloat(),
                "radius" to Configuration.PLAYER_RADIUS,
                "speed" to Configuration.GUARD_SPEED
            )
        )

        for(guardProps in guardPropsList) {
            CreateEntityCommand(gameState, Configuration.GUARDS_NAME, guardProps).execute()
        }
    }
}