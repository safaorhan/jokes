package com.safaorhan.jokes.api

import retrofit2.Response
import retrofit2.http.GET

interface Api {
    @GET("joke/Programming?blacklistFlags=nsfw,religious,political,racist,sexist,explicit&type=single")
    suspend fun getRandomJoke(): Response<Joke>
}
