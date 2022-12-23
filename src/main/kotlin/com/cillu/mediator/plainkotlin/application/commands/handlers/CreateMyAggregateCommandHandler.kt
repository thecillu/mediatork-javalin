package com.cillu.mediator.plainkotlin.application.commands.handlers

import com.cillu.mediator.annotations.CommandHandler
import com.cillu.mediator.commands.ICommandHandler
import com.cillu.mediator.annotations.Inject
import com.cillu.mediator.plainkotlin.application.commands.CreateMyAggregateCommand
import com.cillu.mediator.plainkotlin.domain.models.MyAggregate
import com.cillu.mediator.plainkotlin.domain.repositories.IMyAggregateRepository

@CommandHandler
class CreateMyAggregateCommandHandler: ICommandHandler<CreateMyAggregateCommand>, Exception(){

    @Inject
    lateinit var myAggregateRepository: IMyAggregateRepository

    override fun handle(command: CreateMyAggregateCommand): MyAggregate {
        var aggregate = MyAggregate.create(command.name)
        var result = myAggregateRepository.save(aggregate)
        return result
    }
}