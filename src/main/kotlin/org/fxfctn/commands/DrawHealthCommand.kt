package org.fxfctn.commands

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import ktx.graphics.use
import org.fxfctn.Configuration
import org.fxfctn.uobject.UObjectInterface

class DrawHealthCommand(
    private val renderer: ShapeRenderer,
    private val target: UObjectInterface,
    private val color: Color
): CommandInterface {
    override fun execute() {
        val size = 10f
        renderer.use(ShapeRenderer.ShapeType.Filled) {
            renderer.color = color
            val health: Int = target["health"] as Int
            val percents = health.toFloat() / Configuration.MAX_PLAYER_HEALTH
            val width = Configuration.PLAYGROUND_WIDTH * percents

            renderer.rect(
                Configuration.PLAYGROUND_OFFSET_Y.toFloat(),
                Configuration.PLAYGROUND_OFFSET_Y.toFloat() - size,
                width,
                size
            )
        }
        renderer.use(ShapeRenderer.ShapeType.Line) {
            renderer.color = Color.WHITE

            renderer.rect(
                Configuration.PLAYGROUND_OFFSET_Y.toFloat(),
                Configuration.PLAYGROUND_OFFSET_Y.toFloat() - size,
                Configuration.PLAYGROUND_WIDTH.toFloat(),
                size
            )
        }
    }
}