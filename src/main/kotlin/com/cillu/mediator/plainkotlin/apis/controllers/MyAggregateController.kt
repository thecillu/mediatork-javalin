package com.cillu.mediator.plainkotlin.apis.controllers

import com.cillu.mediator.IMediator
import com.cillu.mediator.plainkotlin.application.commands.CreateMyAggregateCommand
import com.cillu.mediator.plainkotlin.application.commands.DeleteMyAggregateCommand
import com.cillu.mediator.plainkotlin.application.commands.UpdateMyAggregateCommand
import com.cillu.mediator.plainkotlin.application.queries.GetMyAggregateQuery
import com.cillu.mediator.plainkotlin.application.queries.GetMyAggregatesQuery
import com.cillu.mediator.plainkotlin.domain.dtos.MyAggregateDto
import com.cillu.mediator.plainkotlin.domain.models.MyAggregate
import io.javalin.apibuilder.CrudHandler
import io.javalin.http.Context
import io.javalin.http.HttpStatus
import java.util.*

class MyAggregateController: CrudHandler {
    var mediator: IMediator

    constructor( mediator: IMediator) {
        this.mediator = mediator
    }

    override fun create(ctx: Context) {
        var command = ctx.bodyAsClass(CreateMyAggregateCommand::class.java)
        val myaggregate = mediator.send(command) as MyAggregate
        ctx.status(201)
        ctx.json(MyAggregateDto.from(myaggregate!!))
    }

    override fun delete(ctx: Context, resourceId: String) {
        mediator.send(DeleteMyAggregateCommand(UUID.fromString(ctx.pathParam("id"))))
        ctx.status(200)
        ctx.status(HttpStatus.OK)
    }

    override fun getAll(ctx: Context) {
        val aggregates = mediator.send(GetMyAggregatesQuery()) as List<MyAggregate>
        ctx.status(200)
        ctx.json(MyAggregateDto.listFrom(aggregates))
    }

    override fun getOne(ctx: Context, resourceId: String) {
        val myaggregate = mediator.send(GetMyAggregateQuery(UUID.fromString(ctx.pathParam("id")))) as MyAggregate
        ctx.status(200)
        ctx.json(MyAggregateDto.from(myaggregate!!))
    }

    override fun update(ctx: Context, resourceId: String) {
        var command = ctx.bodyAsClass(UpdateMyAggregateCommand::class.java)
        val myaggregate = mediator.send(command) as MyAggregate
        ctx.status(200)
        ctx.json(MyAggregateDto.from(myaggregate!!))
    }

}

