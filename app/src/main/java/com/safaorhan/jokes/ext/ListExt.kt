package com.safaorhan.jokes.ext

fun <T> List<T>.removeRepetitive(): List<T> {
    if (size < 2) return this

    val mList = mutableListOf<T>()

    for ((i, element) in withIndex()) {
        if (i == 0 || get(i) != get(i - 1)) mList.add(element)
    }

    return mList.toList()
}
