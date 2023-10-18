package com.contents.weather.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map

object EventBus {
    private val event = MutableSharedFlow<Any?>(replay = 1)
    val mutableEvent = event.asSharedFlow()

    suspend fun post(event: Any?) {
        this.event.emit(event)
    }

    inline fun <reified T> subscribe(): Flow<T> {
        return mutableEvent.filter { it is T }.map { it as T }
    }
}