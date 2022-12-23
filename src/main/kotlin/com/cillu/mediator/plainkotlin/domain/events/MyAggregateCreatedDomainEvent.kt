package com.cillu.mediator.plainkotlin.domain.events

import com.cillu.mediator.domainevents.DomainEvent
import java.util.*

class MyAggregateCreatedDomainEvent : DomainEvent {
    val id:UUID
    val name: String

    constructor(idEvent:UUID, id: UUID, name:String) : super(idEvent) {
        this.id = id
        this.name = name
    }
}