package com.cillu.mediator.plainkotlin.application.commands

import com.cillu.mediator.commands.Command
import java.util.UUID

class UpdateMyAggregateCommand: Command {

    lateinit var name:String
    lateinit var id: UUID

    constructor(idEvent: UUID, id: UUID, name:String) : super(idEvent) {
        this.name = name;
        this.id = id
    }

    constructor(): super(UUID.randomUUID())

}
