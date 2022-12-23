package com.cillu.mediator.plainkotlin.domain.models

import com.cillu.mediator.domain.Aggregate
import com.cillu.mediator.plainkotlin.domain.events.MyAggregateCreatedDomainEvent
import com.cillu.mediator.plainkotlin.domain.events.MyAggregateDeletedDomainEvent
import com.cillu.mediator.plainkotlin.domain.events.MyAggregateUpdatedDomainEvent
import java.util.UUID


class MyAggregate private constructor(name: String): Aggregate(){
    lateinit var id: UUID
    var name: String = name

    companion object {
        fun create(name: String): MyAggregate {
            var aggregate = MyAggregate(name)
            aggregate.id = UUID.randomUUID()
            aggregate.addDomainEvent(MyAggregateCreatedDomainEvent(UUID.randomUUID(), aggregate.id, aggregate.name))
            return aggregate
        }
    }

    fun markAsDeleted(){
        addDomainEvent(MyAggregateDeletedDomainEvent(UUID.randomUUID(), id, name))
    }

    fun update(name: String) {
        this.name = name
        addDomainEvent(MyAggregateUpdatedDomainEvent(UUID.randomUUID(), this.id, this.name))
    }
}