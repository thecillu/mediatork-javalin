package com.cillu.mediator.plainkotlin.infrastructure.repositories

import com.cillu.mediator.IMediator
import com.cillu.mediator.annotations.Inject
import com.cillu.mediator.plainkotlin.domain.models.MyAggregate
import com.cillu.mediator.plainkotlin.domain.repositories.IMyAggregateRepository
import java.util.*

class MyAggregateRepository: IMyAggregateRepository {

    @Inject
    lateinit var mediator: IMediator

    var aggregates: MutableMap<UUID, MyAggregate> = mutableMapOf()

    override fun findAll(): List<MyAggregate> {
        return aggregates.values.toList()
    }

    override fun find(id: UUID): MyAggregate?{
        return aggregates[id]
    }

    override fun save(aggregate: MyAggregate): MyAggregate {
        aggregates[aggregate.id] = aggregate
        mediator.raiseDomainEvents(aggregate)
        return aggregate
    }

    override fun delete(aggregate: MyAggregate): MyAggregate?  {
        val result = aggregates.remove(aggregate.id)
        mediator.raiseDomainEvents(aggregate)
        return result
    }
}