package com.cillu.mediator.plainkotlin.domain.events.integrations

import com.cillu.mediator.integrationevents.IntegrationEvent
import java.util.*

class MyAggregateCreatedIntegrationEvent : IntegrationEvent {
    val id:UUID
    val name: String

    constructor(idEvent:UUID, id: UUID, name:String) : super(idEvent) {
        this.id = id
        this.name = name
    }
}