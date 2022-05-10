package com.example.projetoestrela

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import java.util.concurrent.TimeUnit


class AppRetrofit {

    fun client()= OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    fun gson(): Gson = GsonBuilder().create()

    val retrofit =
        Retrofit.Builder()
            .baseUrl("https://api-test-portalpaciente.samel.com.br/api/")
            .client(client())
            .addConverterFactory(GsonConverterFactory.create(gson()))
            .build()!!

    fun apiService(): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}