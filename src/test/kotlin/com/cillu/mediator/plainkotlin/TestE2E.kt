package com.cillu.mediator.plainkotlin

import com.cillu.mediator.plainkotlin.apis.Server
import com.cillu.mediator.plainkotlin.apis.controllers.MyAggregateController
import com.cillu.mediator.plainkotlin.application.MediatorFactory
import com.cillu.mediator.plainkotlin.application.commands.CreateMyAggregateCommand
import com.cillu.mediator.plainkotlin.application.commands.UpdateMyAggregateCommand
import com.cillu.mediator.plainkotlin.domain.models.MyAggregate
import com.google.gson.Gson
import io.javalin.Javalin
import io.javalin.http.Context
import io.javalin.json.JavalinJackson
import io.javalin.testtools.JavalinTest
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertThrows
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestE2E {
    private val mediator = MediatorFactory.getMediator()

    @BeforeEach
    fun config(){
        Server.configure(mediator)
    }

    @Test
    fun `Create myaggregates`() = JavalinTest.test(Server.app) { server, client ->
        var createMyAggregateCommand = CreateMyAggregateCommand(UUID.randomUUID(), "Test")
        var createResult = client.post("/myaggregates", Gson().toJson(createMyAggregateCommand))
        val savedMyAggregate = Gson().fromJson(createResult.body?.string() , MyAggregate::class.java)
        var saveResult = client.get("/myaggregates")
        assertThat(saveResult.code).isEqualTo(200)
        val result: List<MyAggregate> = Gson().fromJson(saveResult.body?.string() , Array<MyAggregate>::class.java).toList()
        assertThat(result.size == 1)
    }

    @Test
    fun `GET all myaggregates`() = JavalinTest.test(Server.app) { server, client ->
        var createMyAggregateCommand = CreateMyAggregateCommand(UUID.randomUUID(), "Test")
        var createResult = client.post("/myaggregates", Gson().toJson(createMyAggregateCommand))
        val savedMyAggregate = Gson().fromJson(createResult.body?.string() , MyAggregate::class.java)
        var saveResult = client.get("/myaggregates")
        assertThat(saveResult.code).isEqualTo(200)
        val result: List<MyAggregate> = Gson().fromJson(saveResult.body?.string() , Array<MyAggregate>::class.java).toList()
        assertThat(result.size == 1)
    }

    @Test
    fun `GET one myaggregate`() = JavalinTest.test(Server.app) { server, client ->
        var createMyAggregateCommand = CreateMyAggregateCommand(UUID.randomUUID(), "Test")
        var createResult = client.post("/myaggregates", Gson().toJson(createMyAggregateCommand))
        val savedMyAggregate = Gson().fromJson(createResult.body?.string() , MyAggregate::class.java)
        var saveResult = client.get("/myaggregates/${savedMyAggregate.id}")
        assertThat(saveResult.code).isEqualTo(200)
        val result: MyAggregate = Gson().fromJson(saveResult.body?.string(), MyAggregate::class.java)
        assertThat(result.id ==  savedMyAggregate.id)
        assertThat(result.name ==  "Test")
    }

    @Test
    fun `DELETE one myaggregate`() = JavalinTest.test(Server.app) { server, client ->
        var createMyAggregateCommand = CreateMyAggregateCommand(UUID.randomUUID(), "Test")
        var createResult = client.post("/myaggregates", Gson().toJson(createMyAggregateCommand))
        val savedMyAggregate = Gson().fromJson(createResult.body?.string() , MyAggregate::class.java)
        var saveResult = client.delete("/myaggregates/${savedMyAggregate.id}")
        assertThat(saveResult.code).isEqualTo(200)
        var result = client.get("/myaggregates/${savedMyAggregate.id}")
        assertThat(result.code).isEqualTo(404)
    }

    @Test
    fun `UPDATE a myaggregate`() = JavalinTest.test(Server.app) { server, client ->
        var createMyAggregateCommand = CreateMyAggregateCommand(UUID.randomUUID(), "Test")
        var createResult = client.post("/myaggregates", Gson().toJson(createMyAggregateCommand))
        val savedMyAggregate = Gson().fromJson(createResult.body?.string() , MyAggregate::class.java)
        var updateMyAggregateCommand = UpdateMyAggregateCommand(UUID.randomUUID(), savedMyAggregate.id, "Test2")
        var updateResult = client.patch("/myaggregates/${savedMyAggregate.id}", Gson().toJson(updateMyAggregateCommand))
        var getResult = client.get("/myaggregates/${savedMyAggregate.id}")
        assertThat(getResult.code).isEqualTo(200)
        val result: MyAggregate = Gson().fromJson(getResult.body?.string(), MyAggregate::class.java)
        assertThat(result.id == savedMyAggregate.id)
        assertThat(result.name == "Test2")
    }
}