package com.cillu.mediator.plainkotlin.application.commands.handlers

import com.cillu.mediator.annotations.CommandHandler
import com.cillu.mediator.commands.ICommandHandler
import com.cillu.mediator.annotations.Inject
import com.cillu.mediator.plainkotlin.application.commands.DeleteMyAggregateCommand
import com.cillu.mediator.plainkotlin.domain.repositories.IMyAggregateRepository
import com.cillu.mediator.plainkotlin.exceptions.MyAggregateNotFoundException

@CommandHandler
class DeleteMyAggregateCommandHandler: ICommandHandler<DeleteMyAggregateCommand>, Exception(){

    @Inject
    lateinit var myAggregateRepository: IMyAggregateRepository

    override fun handle(command: DeleteMyAggregateCommand) {
       var aggregate = myAggregateRepository.find(command.id)
        if (aggregate == null) throw MyAggregateNotFoundException(command.id)
        else {
            aggregate.markAsDeleted()
            myAggregateRepository.delete(aggregate)
        }
    }
}