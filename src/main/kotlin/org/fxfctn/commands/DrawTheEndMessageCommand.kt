package org.fxfctn.commands

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import ktx.graphics.use

class DrawTheEndMessageCommand(
    private val renderer: ShapeRenderer
): CommandInterface {
    override fun execute() {
        renderer.use(ShapeRenderer.ShapeType.Line) {
            renderer.color = Color.WHITE
            renderer.line(630f, 300f, 630f, 360f);
            renderer.line(630f, 360f, 600f, 360f);
            renderer.line(600f, 360f, 600f, 380f);
            renderer.line(600f, 380f, 630f, 380f);
            renderer.line(630f, 380f, 630f, 410f);
            renderer.line(630f, 410f, 650f, 410f);
            renderer.line(650f, 410f, 650f, 380f);
            renderer.line(650f, 380f, 680f, 380f);
            renderer.line(680f, 380f, 680f, 360f);
            renderer.line(680f, 360f, 650f, 360f);
            renderer.line(650f, 360f, 650f, 300f);
            renderer.line(650f, 300f, 630f, 300f);
        }
    }
}