package com.cillu.mediator.plainkotlin.application.domaineventshandlers

import com.cillu.mediator.IMediator
import com.cillu.mediator.annotations.DomainEventHandler
import com.cillu.mediator.annotations.Inject
import com.cillu.mediator.domainevents.IDomainEventHandler
import com.cillu.mediator.plainkotlin.domain.events.MyAggregateCreatedDomainEvent
import com.cillu.mediator.plainkotlin.domain.events.integrations.MyAggregateCreatedIntegrationEvent

@DomainEventHandler
class MyAggregateCreatedDomainEventHandler: IDomainEventHandler<MyAggregateCreatedDomainEvent>, Exception(){

    @Inject
    lateinit var mediator: IMediator

    override fun handle(command: MyAggregateCreatedDomainEvent) {
        mediator.publish(MyAggregateCreatedIntegrationEvent(command.idEvent, command.id, command.name))
        println("## FIRST HANDLE - Executing MyAggregateCreatedDomainEvent: ${command.idEvent}")
    }
}