package com.cillu.mediator.plainkotlin.application.queries

import com.cillu.mediator.queries.Query
import java.util.*

class GetMyAggregateQuery: Query {

    val id: UUID

    constructor(id: UUID): super(UUID.randomUUID()){
        this.id = id
    }
}