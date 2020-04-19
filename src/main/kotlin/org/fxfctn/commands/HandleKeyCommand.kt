package org.fxfctn.commands

import org.fxfctn.Configuration
import org.fxfctn.uobject.UObjectInterface

class HandleKeyCommand(
    private val gameState: UObjectInterface,
    private val xDirection: Int,
    private val yDirection: Int
): CommandInterface {
    override fun execute() {
        val players = gameState[Configuration.PLAYERS_NAME] as MutableList<UObjectInterface>
        val player = players[0]
        val speed = player["speed"] as Int
        val deltaHealth = if (xDirection != 0 || yDirection != 0) Configuration.PLAYER_HEALTH_DELTA else -Configuration.PLAYER_HEALTH_DELTA

        val moveCommand = MoveCommand(
            player,
            (xDirection * speed).toFloat(),
            (yDirection * speed).toFloat()
        )
        val revertMoveCommand = MoveCommand(
            player,
            (-1 * xDirection * speed).toFloat(),
            (-1 * yDirection * speed).toFloat())
        val outOfBoundRule = OutOfBoundsRule(
            player,
            Configuration.PLAYGROUND_OFFSET_X,
            Configuration.PLAYGROUND_OFFSET_Y,
            Configuration.PLAYGROUND_WIDTH + Configuration.PLAYGROUND_OFFSET_X,
            Configuration.PLAYGROUND_HEIGHT + Configuration.PLAYGROUND_OFFSET_Y,
            revertMoveCommand
        )
        val changeHealthCommand = ChangeHealthCommand(player, deltaHealth)
        val finishGameCommand = FinishGameCommand(gameState)
        val emptyHealthRule = EmptyHealthRule(player, finishGameCommand)

        moveCommand.execute()
        outOfBoundRule.execute()

        val guards = gameState[Configuration.GUARDS_NAME] as MutableList<UObjectInterface>

        for (guard in guards) {
            IntersectionRule(player, guard, revertMoveCommand).execute();
        }

        changeHealthCommand.execute()
        emptyHealthRule.execute()
    }
}