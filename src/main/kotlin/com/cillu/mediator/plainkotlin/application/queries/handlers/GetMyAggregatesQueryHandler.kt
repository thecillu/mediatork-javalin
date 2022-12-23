package com.cillu.mediator.plainkotlin.application.queries.handlers

import com.cillu.mediator.annotations.Inject
import com.cillu.mediator.annotations.QueryHandler
import com.cillu.mediator.queries.IQueryHandler
import com.cillu.mediator.plainkotlin.application.queries.GetMyAggregatesQuery
import com.cillu.mediator.plainkotlin.domain.models.MyAggregate
import com.cillu.mediator.plainkotlin.domain.repositories.IMyAggregateRepository

@QueryHandler
class GetMyAggregatesQueryHandler(): IQueryHandler<GetMyAggregatesQuery> {

    @Inject
    lateinit var myAggregateRepository: IMyAggregateRepository

    override fun handle(query: GetMyAggregatesQuery): List<MyAggregate> {
        return myAggregateRepository.findAll()
    }
}