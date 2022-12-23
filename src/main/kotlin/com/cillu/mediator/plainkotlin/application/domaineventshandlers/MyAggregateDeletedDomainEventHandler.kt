package com.cillu.mediator.plainkotlin.application.domaineventshandlers

import com.cillu.mediator.annotations.DomainEventHandler
import com.cillu.mediator.annotations.Inject
import com.cillu.mediator.domainevents.IDomainEventHandler
import com.cillu.mediator.plainkotlin.domain.events.MyAggregateDeletedDomainEvent
import com.cillu.mediator.plainkotlin.domain.repositories.IMyAggregateRepository

@DomainEventHandler
class MyAggregateDeletedDomainEventHandler: IDomainEventHandler<MyAggregateDeletedDomainEvent>, Exception(){

    @Inject
    lateinit var myAggregateRepository: IMyAggregateRepository

    override fun handle(command: MyAggregateDeletedDomainEvent) {
        println("## FIRST HANDLE - Executing MyAggregateDeletedDomainEvent: ${command.idEvent}")
    }
}