package com.safaorhan.jokes.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Joke(
    val joke: String?,
    val message: String?
)
