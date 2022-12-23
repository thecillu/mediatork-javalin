package com.cillu.mediator.plainkotlin.application.domaineventshandlers

import com.cillu.mediator.IMediator
import com.cillu.mediator.annotations.DomainEventHandler
import com.cillu.mediator.annotations.Inject
import com.cillu.mediator.domainevents.IDomainEventHandler
import com.cillu.mediator.plainkotlin.domain.events.MyAggregateUpdatedDomainEvent
import com.cillu.mediator.plainkotlin.domain.events.integrations.MyAggregateUpdatedIntegrationEvent

@DomainEventHandler
class MyAggregateUpdatedDomainEventHandler: IDomainEventHandler<MyAggregateUpdatedDomainEvent>, Exception(){

    @Inject
    lateinit var mediator: IMediator

    override fun handle(command: MyAggregateUpdatedDomainEvent) {
        mediator.publish(MyAggregateUpdatedIntegrationEvent(command.idEvent, command.id, command.name))
        println("## Executing MyAggregateUpdatedDomainEventHandler: ${command.idEvent}")
    }
}