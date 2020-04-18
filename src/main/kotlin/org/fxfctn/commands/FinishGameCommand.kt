package org.fxfctn.commands

import org.fxfctn.uobject.UObjectInterface

class FinishGameCommand(private val gameState: UObjectInterface): CommandInterface {
    override fun execute() {
        gameState["isFinished"] = true
    }
}