package org.fxfctn

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration

import org.fxfctn.application.ApplicationAdapter

fun main() {
    val config = LwjglApplicationConfiguration().apply {
        title = "Save The Hamster!"
        width = Configuration.SCREEN_WIDTH
        height = Configuration.SCREEN_HEIGHT
    }

    val adapter = ApplicationAdapter()

    LwjglApplication(adapter, config)
}
