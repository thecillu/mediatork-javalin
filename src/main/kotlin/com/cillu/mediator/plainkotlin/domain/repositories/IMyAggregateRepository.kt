package com.cillu.mediator.plainkotlin.domain.repositories

import com.cillu.mediator.plainkotlin.domain.models.MyAggregate
import java.util.UUID

interface IMyAggregateRepository {
    fun findAll(): List<MyAggregate>
    fun find(id: UUID): MyAggregate?
    fun save(aggregate: MyAggregate): MyAggregate
    fun delete(aggregate: MyAggregate): MyAggregate?
}