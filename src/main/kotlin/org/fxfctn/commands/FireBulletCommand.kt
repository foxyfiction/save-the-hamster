package org.fxfctn.commands

import org.fxfctn.Configuration
import kotlin.random.Random
import org.fxfctn.uobject.UObjectInterface
import kotlin.math.pow
import kotlin.math.sqrt

class FireBulletCommand(
    private val gameState: UObjectInterface,
    private val initiator: UObjectInterface,
    private val xDirection: Int,
    private val yDirection: Int
): CommandInterface {
    override fun execute() {
        val x = initiator["x"] as Float
        val y = initiator["y"] as Float
        val radius = initiator["radius"] as Int
        val deltaSpeedX = Random.nextInt(5, 10)
        val deltaSpeedY = Random.nextInt(5, 10)
        val directionVector = sqrt((
                deltaSpeedX.toFloat().pow(2) + deltaSpeedY.toFloat().pow(2)))
        val relation = radius.toFloat() / directionVector
        println(xDirection * deltaSpeedX * relation)
        val bulletX = x + xDirection * deltaSpeedX * relation
        val bulletY = y + yDirection * deltaSpeedY * relation
        val bulletProps = mapOf<String, Any>(
            "x" to bulletX,
            "y" to bulletY,
            "deltaSpeedX" to xDirection * deltaSpeedX,
            "deltaSpeedY" to yDirection * deltaSpeedY,
            "radius" to 2,
            "damage" to Random.nextInt(100, 10000)
        )
        CreateEntityCommand(gameState, Configuration.BULLETS_NAME, bulletProps).execute()

    }
}