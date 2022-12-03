package com.safaorhan.jokes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.safaorhan.jokes.async.AsyncResult.*
import com.safaorhan.jokes.ext.invoke
import com.safaorhan.jokes.ext.update
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JokeViewModel @Inject constructor(
    private val repository: JokeRepository
) : ViewModel() {
    val state = MutableLiveData(JokeState())

    init {
        fetchRandomJoke()
    }

    fun onRefreshButtonClick() = fetchRandomJoke()

    private fun fetchRandomJoke(): Job = viewModelScope.launch {
        state.update { copy(isLoading = true) }

        when (val result = repository.getRandomJoke()) {
            is Success -> when (state().joke) {
                result.data -> fetchRandomJoke()
                else -> state.update {
                    copy(joke = result.data, isLoading = false)
                }
            }
            is Error -> state.update {
                copy(error = result.message, isLoading = false)
            }
            is Cancelled -> state.update {
                copy(isLoading = false)
            }
        }
    }
}
