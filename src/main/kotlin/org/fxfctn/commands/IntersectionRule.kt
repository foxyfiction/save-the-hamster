package org.fxfctn.commands

import kotlin.math.sqrt
import kotlin.math.pow
import org.fxfctn.uobject.UObjectInterface

class IntersectionRule(
    private val firstShape: UObjectInterface,
    private val secondShape: UObjectInterface,
    private val command: CommandInterface
): CommandInterface {
    override fun execute() {
        val x1 = firstShape["x"] as Float
        val y1 = firstShape["y"] as Float
        val x2 = secondShape["x"] as Float
        val y2 = secondShape["y"] as Float
        val radius1 = firstShape["radius"] as Int
        val radius2 = secondShape["radius"] as Int
        val rho = sqrt(((x2 - x1).pow(2) + (y2 - y1).pow(2)))

        if (radius1 + radius2 >= rho) {
            command.execute()
        }
    }

}