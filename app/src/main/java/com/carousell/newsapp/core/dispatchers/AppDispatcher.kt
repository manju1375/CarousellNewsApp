package com.carousell.newsapp.core.dispatchers

import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * Android dispatcher for work.
 * Internally uses Coroutine Dispatchers for dispatching work to background threads.
 */
class AppDispatcher @Inject constructor(x:Int) : Dispatcher {

    override val main = Dispatchers.Main

    override val io = Dispatchers.IO

    override val db = Dispatchers.IO

    override val computation = Dispatchers.Default

}
