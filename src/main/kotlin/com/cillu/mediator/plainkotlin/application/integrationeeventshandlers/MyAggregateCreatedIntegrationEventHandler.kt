package com.cillu.mediator.plainkotlin.application.integrationeeventshandlers

import com.cillu.mediator.annotations.Inject
import com.cillu.mediator.annotations.IntegrationEventHandler
import com.cillu.mediator.integrationevents.IIntegrationEventHandler
import com.cillu.mediator.plainkotlin.domain.events.integrations.MyAggregateCreatedIntegrationEvent
import com.cillu.mediator.plainkotlin.domain.services.ICounterService

@IntegrationEventHandler
class MyAggregateCreatedIntegrationEventHandler: IIntegrationEventHandler<MyAggregateCreatedIntegrationEvent>, Exception(){

    @Inject
    lateinit var counterService: ICounterService

    override fun handle(command: MyAggregateCreatedIntegrationEvent) {
        counterService.increment()
        println("Executing MyAggregateCreatedIntegrationEventHandler: ${counterService.getValue()}")
    }
}