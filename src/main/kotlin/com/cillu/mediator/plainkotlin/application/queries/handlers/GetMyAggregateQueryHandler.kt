package com.cillu.mediator.plainkotlin.application.queries.handlers

import com.cillu.mediator.annotations.Inject
import com.cillu.mediator.annotations.QueryHandler
import com.cillu.mediator.queries.IQueryHandler
import com.cillu.mediator.plainkotlin.application.queries.GetMyAggregateQuery
import com.cillu.mediator.plainkotlin.domain.models.MyAggregate
import com.cillu.mediator.plainkotlin.domain.repositories.IMyAggregateRepository
import com.cillu.mediator.plainkotlin.exceptions.MyAggregateNotFoundException

@QueryHandler
class GetMyAggregateQueryHandler(): IQueryHandler<GetMyAggregateQuery>{

    @Inject
    lateinit var myAggregateRepository: IMyAggregateRepository

    override fun handle(query: GetMyAggregateQuery): MyAggregate {
        val result = myAggregateRepository.find(query.id)
        if (result == null) throw MyAggregateNotFoundException(query.id)
        else return result!!
    }
}