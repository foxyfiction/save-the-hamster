package org.fxfctn.commands

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import org.fxfctn.uobject.UObjectInterface
import ktx.graphics.use

class DrawMemberCommand(
    private val renderer: ShapeRenderer,
    private val target: UObjectInterface,
    private val color: Color
): CommandInterface {
    override fun execute() {
        renderer.use(ShapeRenderer.ShapeType.Filled) {
            renderer.color = color
            val positionX: Float = target["x"] as Float
            val positionY: Float = target["y"] as Float
            val radius: Int = target["radius"] as Int
            renderer.circle(positionX, positionY, radius.toFloat())
        }
    }
}