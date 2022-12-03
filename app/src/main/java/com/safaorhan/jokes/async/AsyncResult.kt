package com.safaorhan.jokes.async

sealed class AsyncResult<out T> {
    class Success<out T>(val data: T) : AsyncResult<T>()
    class Error(val message: String) : AsyncResult<Nothing>()
    object Cancelled : AsyncResult<Nothing>()
}
