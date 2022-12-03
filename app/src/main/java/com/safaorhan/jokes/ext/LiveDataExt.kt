package com.safaorhan.jokes.ext

import androidx.lifecycle.MutableLiveData

operator fun <T> MutableLiveData<T>.invoke() = value!!

fun <T> MutableLiveData<T>.update(
    transform: T.() -> T
) {
    value = value!!.transform()
}
