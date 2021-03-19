package com.example.propertymanagement.data.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

object Coroutines {
    fun main(work:suspend (() -> Unit)) = CoroutineScope(Dispatchers.Main).launch{
        work()
    }
}