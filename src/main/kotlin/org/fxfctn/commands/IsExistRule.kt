package org.fxfctn.commands

import org.fxfctn.uobject.UObjectInterface

class IsExistRule(private val target: UObjectInterface?, private val command: CommandInterface): CommandInterface {
    override fun execute() {
        if (target != null) {
            command.execute()
        }
    }
}