package org.fxfctn.application

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import ktx.app.KtxApplicationAdapter
import ktx.app.clearScreen

import org.fxfctn.commands.HandleKeyCommand
import org.fxfctn.commands.InitializeGameCommand
import org.fxfctn.commands.DrawMemberCommand
import org.fxfctn.commands.DrawHealthCommand
import org.fxfctn.commands.DrawTheEndMessageCommand
import org.fxfctn.Configuration
import org.fxfctn.uobject.UObject
import org.fxfctn.uobject.UObjectInterface

class ApplicationAdapter : KtxApplicationAdapter {
    private lateinit var renderer: ShapeRenderer
    private lateinit var gameState: UObjectInterface
    private var guards = emptyList<UObjectInterface>()

    override fun create() {
        renderer = ShapeRenderer()
        gameState = UObject()
        InitializeGameCommand(gameState).execute()
    }

    override fun render() {
        handleInput()
        logic()
        draw()
    }

    private fun handleInput() {
        var xDirection: Int = 0
        var yDirection: Int = 0

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            yDirection = 1
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            xDirection = 1
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            yDirection = -1
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            xDirection = -1
        }

        HandleKeyCommand(gameState, xDirection, yDirection).execute()
    }
    private fun logic() {
    }
    private fun draw() {
        clearScreen(0f, 0f, 0f, 0f)
        val isFinished = gameState["isFinished"] as Boolean

        if (isFinished) {
            DrawTheEndMessageCommand(renderer).execute()

            return
        }

        val players = gameState[Configuration.PLAYERS_NAME] as MutableList<UObjectInterface>
        val player = players[0]

        DrawMemberCommand(renderer, player, Color.ORANGE).execute()
        DrawHealthCommand(renderer, player, Color.GREEN).execute()

        val guards = gameState[Configuration.GUARDS_NAME] as MutableList<UObjectInterface>
        for (guard in guards) {
            DrawMemberCommand(renderer, guard, Color.ROYAL).execute()
        }
    }
}
