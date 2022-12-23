package com.cillu.mediator.plainkotlin

import com.cillu.mediator.plainkotlin.apis.controllers.MyAggregateController
import com.cillu.mediator.plainkotlin.application.MediatorFactory
import com.cillu.mediator.plainkotlin.application.commands.CreateMyAggregateCommand
import io.javalin.http.Context
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*

class TestHandler {

    private val ctx = mockk<Context>(relaxed = true)
    private val mediator = MediatorFactory.getMediator()
    private val myAggregateController = MyAggregateController(mediator)

    @Test
    fun `POST to create myaggregate gives 201`() {
        var createMyAggregateCommand = CreateMyAggregateCommand(UUID.randomUUID(), "Test")
        every { ctx.bodyAsClass(CreateMyAggregateCommand::class.java) } returns createMyAggregateCommand
        myAggregateController.create(ctx)
        verify { ctx.status(201) }
    }

    @Test
    fun `GET a not existent myaggregate gives Exception`() {
        var id = "06938480-71bf-45eb-0000-d582ea127895"
        every { ctx.pathParam("id") } returns id
        assertThrows<Exception> {
            myAggregateController.getOne(ctx, id)
        }
    }

    @Test
    fun `GET all myaggregates`() {
        myAggregateController.getAll(ctx)
        verify { ctx.status(200) }
    }

    @Test
    fun `DELETE a not existent myaggregate gives Exception`() {
        var id = "06938480-71bf-45eb-0000-d582ea127895"
        every { ctx.pathParam("id") } returns id
        assertThrows<Exception> {
            myAggregateController.delete(ctx, id)
        }
    }

    @Test
    fun `UPDATE a not existent myaggregate gives Exception`() {
        var id = "06938480-71bf-45eb-0000-d582ea127895"
        every { ctx.pathParam("id") } returns id
        assertThrows<Exception> {
            myAggregateController.update(ctx, id)
        }
    }

    /*@Test(expected = BadRequestResponse::class)
    fun `POST to create users throws for invalid username`() {
        every { ctx.queryParam("username") } returns null
        myAggregateController.create(ctx) // the handler we're testing
    }*/
}