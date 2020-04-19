package org.fxfctn.commands

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import ktx.graphics.use
import org.fxfctn.uobject.UObjectInterface

class DrawLevelIndicatorCommand(
    private val renderer: ShapeRenderer,
    private val gameState: UObjectInterface
): CommandInterface {
    override fun execute() {
        val level = gameState["level"] as Int
        val levelRadius = 5f
        val gap = levelRadius * 3
        var levelX = 40 + levelRadius
        val levelY = 20f

        for (lvl in 1..level) {
            renderer.use(ShapeRenderer.ShapeType.Filled) {
                renderer.color = Color.GOLD
                renderer.circle(levelX, levelY, levelRadius)
            }

            levelX += gap
        }
    }
}