package com.safaorhan.jokes.ext

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot

inline fun <reified T : Any> LiveData<T>.observeIntoList(): List<T> {
    val observer = mockk<Observer<T>>()

    val slot = slot<T>()

    val list = mutableListOf<T>()

    every { observer.onChanged(capture(slot)) } answers { list.add(slot.captured) }

    observeForever(observer)

    return list
}
