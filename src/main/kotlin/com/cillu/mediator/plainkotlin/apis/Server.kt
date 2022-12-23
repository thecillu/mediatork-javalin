package com.cillu.mediator.plainkotlin.apis

import com.cillu.mediator.IMediator
import com.cillu.mediator.plainkotlin.apis.controllers.MyAggregateController
import com.cillu.mediator.plainkotlin.exceptions.MyAggregateNotFoundException
import com.sun.net.httpserver.HttpServer
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.crud
import io.javalin.http.HttpStatus

class Server {

    companion object{
        private const val serverPort = 8000
        lateinit var app: Javalin

        fun configure(mediator: IMediator) {
            app = Javalin.create()

            app.routes {
                crud("/myaggregates/{id}", MyAggregateController(mediator))
            }

            // HTTP exceptions
            app.exception(Exception::class.java) { e, ctx ->
                var cause = e.cause
                when (cause) {
                    is MyAggregateNotFoundException -> {
                        println(e.printStackTrace())
                        ctx.status(HttpStatus.NOT_FOUND)
                    }
                    else -> {
                        println(e.printStackTrace())
                        ctx.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    }
                }

            }
        }

        fun start(){
            app.start(serverPort)
        }
    }
}