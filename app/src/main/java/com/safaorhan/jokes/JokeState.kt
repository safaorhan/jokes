package com.safaorhan.jokes

data class JokeState(
    val error: String? = null,
    val joke: String? = null,
    val isLoading: Boolean = false
)
