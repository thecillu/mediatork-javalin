package com.cillu.mediator.plainkotlin.domain.services

interface ICounterService {
    fun increment()
    fun decrement()
    fun getValue(): Int
}