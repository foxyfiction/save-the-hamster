package org.fxfctn.application

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import ktx.app.KtxApplicationAdapter
import ktx.app.clearScreen

import org.fxfctn.Configuration
import org.fxfctn.commands.*
import org.fxfctn.uobject.UObject
import org.fxfctn.uobject.UObjectInterface

class ApplicationAdapter : KtxApplicationAdapter {
    private lateinit var renderer: ShapeRenderer
    private lateinit var batch: SpriteBatch
    private lateinit var gameState: UObjectInterface

    override fun create() {
        renderer = ShapeRenderer()
        batch = SpriteBatch()
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
        ManageGuardsCommand(gameState).execute()
        ManageBulletsCommand(gameState).execute()
    }
    private fun draw() {
        clearScreen(0f, 0f, 0f, 0f)

        DrawPlaygroundBoundsCommand(renderer).execute()
        DrawLevelIndicatorCommand(renderer, gameState).execute()

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

        val bullets = gameState[Configuration.BULLETS_NAME] as MutableList<UObjectInterface>
        for (bullet in bullets) {
            DrawMemberCommand(renderer, bullet, Color.WHITE).execute()
        }
    }
}
