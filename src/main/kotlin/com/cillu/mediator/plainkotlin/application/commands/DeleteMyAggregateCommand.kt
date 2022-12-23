package com.cillu.mediator.plainkotlin.application.commands

import com.cillu.mediator.commands.Command
import java.util.UUID

class DeleteMyAggregateCommand: Command {

    lateinit var id:UUID


    constructor(id:UUID) : super(UUID.randomUUID()) {
        this.id = id;
    }

    constructor(): super(UUID.randomUUID())

}
