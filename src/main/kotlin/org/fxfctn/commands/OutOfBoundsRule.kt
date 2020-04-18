package org.fxfctn.commands

import org.fxfctn.uobject.UObjectInterface

class OutOfBoundsRule(
    private val target: UObjectInterface,
    private val width: Int,
    private val height: Int,
    private val command: CommandInterface
): CommandInterface {
    override fun execute() {
        val x = target["x"] as Float
        val y = target["y"] as Float
        val radius = target["radius"] as Int

        if (x < radius || y < radius || x > width - radius || y > height - radius) {
            command.execute()
        }
    }
}