package com.everis.aplication.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

open class DispatcherProvider {
    open val IO: CoroutineDispatcher = Dispatchers.IO
    open val Main: CoroutineDispatcher = Dispatchers.Main
    open val Unconfined: CoroutineDispatcher = Dispatchers.Unconfined
}