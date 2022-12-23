package com.cillu.mediator.plainkotlin.application.commands.handlers

import com.cillu.mediator.annotations.CommandHandler
import com.cillu.mediator.commands.ICommandHandler
import com.cillu.mediator.annotations.Inject
import com.cillu.mediator.plainkotlin.application.commands.UpdateMyAggregateCommand
import com.cillu.mediator.plainkotlin.domain.models.MyAggregate
import com.cillu.mediator.plainkotlin.domain.repositories.IMyAggregateRepository
import com.cillu.mediator.plainkotlin.exceptions.MyAggregateNotFoundException

@CommandHandler
class UpdateMyAggregateCommandHandler: ICommandHandler<UpdateMyAggregateCommand>, Exception(){

    @Inject
    lateinit var myAggregateRepository: IMyAggregateRepository

    override fun handle(command: UpdateMyAggregateCommand): MyAggregate {
        var aggregate = myAggregateRepository.find(command.id) ?: throw MyAggregateNotFoundException(command.id)
        aggregate.update(command.name)
        return myAggregateRepository.save(aggregate)
    }
}