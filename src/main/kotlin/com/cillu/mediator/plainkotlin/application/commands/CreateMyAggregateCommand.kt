package com.cillu.mediator.plainkotlin.application.commands

import com.cillu.mediator.commands.Command
import java.util.UUID

class CreateMyAggregateCommand: Command {

    lateinit var name:String

    constructor(idEvent: UUID, name:String) : super(idEvent) {
        this.name = name;
    }

    constructor(): super(UUID.randomUUID())

}
