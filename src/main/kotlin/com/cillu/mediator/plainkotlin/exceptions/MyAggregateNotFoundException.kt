package com.cillu.mediator.plainkotlin.exceptions

import java.util.UUID

class MyAggregateNotFoundException(val id: UUID):
    Exception( "MyAggregate with ${id} not found") {
}