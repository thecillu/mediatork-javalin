package com.cillu.mediator.plainkotlin.application

import com.cillu.mediator.IMediator
import com.cillu.mediator.MediatorFactory
import com.cillu.mediator.messagebrokers.MessageBrokerFactory
import com.cillu.mediator.messagebrokers.rabbitmq.RabbitMQConfiguration
import com.cillu.mediator.plainkotlin.domain.repositories.IMyAggregateRepository
import com.cillu.mediator.plainkotlin.domain.services.ICounterService
import com.cillu.mediator.plainkotlin.infrastructure.repositories.MyAggregateRepository
import com.cillu.mediator.plainkotlin.infrastructure.services.CounterService
import com.cillu.mediator.registry.ServiceRegistry

class MediatorFactory {

    companion object{
        fun getMediator(): IMediator {
            var serviceRegistry = ServiceRegistry()
            serviceRegistry.register(IMyAggregateRepository::class.java, MyAggregateRepository())
            serviceRegistry.register(ICounterService::class.java, CounterService())
            var rabbitMQConfiguration = RabbitMQConfiguration(
                host = "localhost",
                port = 5672,
                username = "guest",
                password = "guest",
                useSslProtocol = false,
                exchangeName = "plain-kotlin-topic",
                queueName = "plain-kotlin",
                consumerRetryLimit = 5
            )
            return MediatorFactory.getDefaultMediator(
                listOf("com.cillu.mediator.plainkotlin"),
                serviceRegistry, MessageBrokerFactory.getMessageBroker(rabbitMQConfiguration)
            )
        }
    }
}