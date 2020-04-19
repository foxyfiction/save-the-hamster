package org.fxfctn.commands

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import ktx.graphics.use
import org.fxfctn.Configuration

class DrawPlaygroundBoundsCommand(
    private val renderer: ShapeRenderer
): CommandInterface {
    override fun execute() {
        renderer.use(ShapeRenderer.ShapeType.Filled) {
            renderer.color = Color.TEAL
            renderer.rect(
                Configuration.PLAYGROUND_OFFSET_X.toFloat(),
                Configuration.PLAYGROUND_OFFSET_Y.toFloat(),
                Configuration.PLAYGROUND_WIDTH.toFloat(),
                Configuration.PLAYGROUND_HEIGHT.toFloat()
            );
        }
        renderer.use(ShapeRenderer.ShapeType.Line) {
            renderer.color = Color.WHITE
            renderer.rect(
                Configuration.PLAYGROUND_OFFSET_X.toFloat(),
                Configuration.PLAYGROUND_OFFSET_Y.toFloat(),
                Configuration.PLAYGROUND_WIDTH.toFloat(),
                Configuration.PLAYGROUND_HEIGHT.toFloat()
            );
        }
    }
}