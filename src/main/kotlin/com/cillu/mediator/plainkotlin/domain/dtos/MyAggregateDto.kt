package com.cillu.mediator.plainkotlin.domain.dtos

import com.cillu.mediator.plainkotlin.domain.models.MyAggregate
import java.util.UUID


class MyAggregateDto {

    lateinit var id:UUID
    lateinit var name:String

    companion object {
        fun from(myAggregate: MyAggregate): MyAggregateDto {
            var myAggregateDto = MyAggregateDto()
            myAggregateDto.id = myAggregate.id
            myAggregateDto.name = myAggregate.name
            return myAggregateDto
        }

        fun listFrom(myAggregates: List<MyAggregate>): List<MyAggregateDto> {
            var myAggregatesDto = mutableListOf<MyAggregateDto>()
            myAggregates.forEach{
                    exampleAggregate -> myAggregatesDto.add(from(exampleAggregate))
            }
            return myAggregatesDto
        }
    }
}