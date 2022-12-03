package com.safaorhan.jokes

import com.safaorhan.jokes.api.Api
import com.safaorhan.jokes.async.AsyncResult
import com.safaorhan.jokes.async.AsyncResult.*
import kotlinx.coroutines.CancellationException
import javax.inject.Inject

class JokeRepository @Inject constructor(
    private val api: Api
) {
    suspend fun getRandomJoke() = try {
        val response = api.getRandomJoke()

        when {
            response.isSuccessful -> response.body()?.joke?.let { Success(it) }
                ?: Error("No joke in successful joke response.")
            else -> response.body()?.message?.let { Error("$it. Code: ${response.code()}") }
                ?: Error("No error message in error response. Code: ${response.code()}")
        }
    } catch (ex: CancellationException) {
        Cancelled
    } catch (ex: Exception) {
        Error("Check your internet connectivity.")
    }
}
