package com.cillu.mediator.plainkotlin.application.domaineventshandlers

import com.cillu.mediator.annotations.DomainEventHandler
import com.cillu.mediator.annotations.Inject
import com.cillu.mediator.domainevents.IDomainEventHandler
import com.cillu.mediator.plainkotlin.domain.events.MyAggregateCreatedDomainEvent
import com.cillu.mediator.plainkotlin.domain.repositories.IMyAggregateRepository

@DomainEventHandler
class MyAggregateCreated2DomainEventHandler: IDomainEventHandler<MyAggregateCreatedDomainEvent>, Exception(){

    @Inject
    lateinit var myAggregateRepository: IMyAggregateRepository

    override fun handle(command: MyAggregateCreatedDomainEvent) {
        println("## SECOND HANDLE - Executing MyAggregateCreatedDomainEvent: ${command.idEvent}")
    }
}