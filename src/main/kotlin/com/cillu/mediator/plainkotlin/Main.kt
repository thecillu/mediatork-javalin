package com.cillu.mediator.plainkotlin

import com.cillu.mediator.plainkotlin.apis.Server
import com.cillu.mediator.plainkotlin.application.MediatorFactory


class Main

fun main() {
    Server.configure(MediatorFactory.getMediator())
    Server.start()
}




