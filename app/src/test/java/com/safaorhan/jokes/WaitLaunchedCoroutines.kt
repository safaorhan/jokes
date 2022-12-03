package com.safaorhan.jokes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking

fun ViewModel.waitLaunchedCoroutines() = runBlocking {
    val job = viewModelScope.coroutineContext[Job]
    val children = job?.children.orEmpty().toList()
    children.forEach { it.join() }
}
