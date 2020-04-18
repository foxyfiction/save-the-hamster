package org.fxfctn.commands

import org.fxfctn.uobject.UObject
import org.fxfctn.commands.CreateEntityCommand
import org.fxfctn.Configuration
import org.fxfctn.uobject.UObjectInterface

class InitializeGameCommand(private val gameState: UObjectInterface): CommandInterface {
    override fun execute() {
        gameState[Configuration.PLAYERS_NAME] = mutableListOf<UObjectInterface>()
        gameState[Configuration.GUARDS_NAME] = mutableListOf<UObjectInterface>()
        gameState[Configuration.BULLETS_NAME] = mutableListOf<UObjectInterface>()
        gameState["isFinished"] = false

        val playerProps = mapOf(
            "x" to (Configuration.SCREEN_WIDTH / 2).toFloat(),
            "y" to (Configuration.SCREEN_HEIGHT / 2).toFloat(),
            "radius" to Configuration.PLAYER_RADIUS,
            "speed" to Configuration.PLAYER_SPEED,
            "health" to Configuration.PLAYER_HEALTH
        )

        CreateEntityCommand(gameState, Configuration.PLAYERS_NAME, playerProps).execute()

        val guardPropsList = listOf<Map<String, Any>>(
            mapOf(
                "x" to (Configuration.SCREEN_WIDTH / 4).toFloat(),
                "y" to (Configuration.SCREEN_HEIGHT / 4).toFloat(),
                "radius" to Configuration.PLAYER_RADIUS,
                "speed" to 0f
            ),
            mapOf(
                "x" to (Configuration.SCREEN_WIDTH / 4 * 3).toFloat(),
                "y" to (Configuration.SCREEN_HEIGHT / 4 * 3).toFloat(),
                "radius" to Configuration.PLAYER_RADIUS,
                "speed" to 0f
            )
        )

        for(guardProps in guardPropsList) {
            CreateEntityCommand(gameState, Configuration.GUARDS_NAME, guardProps).execute()
        }
    }
}