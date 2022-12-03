package com.safaorhan.jokes.api

import com.squareup.moshi.Moshi
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

fun testApi(server: MockWebServer) = Retrofit.Builder()
    .client(OkHttpClient.Builder().dispatcher(Dispatcher(CurrentThreadExecutor())).build())
    .baseUrl(server.url("/"))
    .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
    .build()
    .create(Api::class.java)
