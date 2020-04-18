package org.fxfctn.commands

import org.fxfctn.uobject.UObjectInterface

class MoveCommand(
    private val target: UObjectInterface,
    private val x: Float,
    private val y: Float
): CommandInterface {
    override fun execute() {
        val positionX: Float = target["x"] as Float
        val positionY: Float = target["y"] as Float

        target["x"] = positionX + x
        target["y"] = positionY + y
    }
}