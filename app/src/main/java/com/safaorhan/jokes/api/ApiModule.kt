package com.safaorhan.jokes.api

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    fun provideMoshiConverterFactory() = MoshiConverterFactory.create(Moshi.Builder().build())

    @Provides
    fun provideRetrofit(moshiConverterFactory: MoshiConverterFactory) = Retrofit.Builder()
        .baseUrl("https://v2.jokeapi.dev/")
        .addConverterFactory(moshiConverterFactory)
        .build()

    @Provides
    fun provideApi(retrofit: Retrofit) = retrofit.create(Api::class.java)
}
