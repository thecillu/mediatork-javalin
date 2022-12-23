package com.cillu.mediator.plainkotlin.application.integrationeeventshandlers

import com.cillu.mediator.annotations.Inject
import com.cillu.mediator.annotations.IntegrationEventHandler
import com.cillu.mediator.integrationevents.IIntegrationEventHandler
import com.cillu.mediator.plainkotlin.domain.events.integrations.MyAggregateUpdatedIntegrationEvent
import com.cillu.mediator.plainkotlin.domain.services.ICounterService

@IntegrationEventHandler
class MyAggregateUpdatedIntegrationEventHandler: IIntegrationEventHandler<MyAggregateUpdatedIntegrationEvent>, Exception(){

    @Inject
    lateinit var counterService: ICounterService

    override fun handle(command: MyAggregateUpdatedIntegrationEvent) {
        counterService.increment()
        println("Executing MyAggregateUpdatedIntegrationEventHandler: ${counterService.getValue()}")
    }
}