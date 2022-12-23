package com.cillu.mediator.plainkotlin.infrastructure.services

import com.cillu.mediator.plainkotlin.domain.services.ICounterService

class CounterService: ICounterService {

    var counter:Int = 0

    override fun increment() {
        counter++
    }

    override fun decrement() {
        counter--
    }

    override fun getValue(): Int {
        return counter
    }

}