package com.safaorhan.jokes

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.safaorhan.jokes.async.AsyncResult
import com.safaorhan.jokes.async.AsyncResult.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JokeViewModel @Inject constructor(
    private val repository: JokeRepository
) : ViewModel() {
    val state = MutableStateFlow(JokeState())

    init {
        fetchRandomJoke()
    }

    fun onRefreshButtonClick() = fetchRandomJoke()

    private fun fetchRandomJoke() = viewModelScope.launch {
        state.update { it.copy(isLoading = true) }

        when (val result = repository.getRandomJoke()) {
            is Success -> state.update {
                it.copy(joke = result.data, isLoading = false)
            }
            is Error -> state.update {
                it.copy(error = result.message, isLoading = false)
            }
            is Cancelled -> state.update {
                it.copy(isLoading = false)
            }
        }
    }
}
