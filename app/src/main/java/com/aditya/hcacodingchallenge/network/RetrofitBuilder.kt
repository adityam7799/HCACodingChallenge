package com.aditya.hcacodingchallenge.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Class for configuring retrofit for network calls.
 */
object RetrofitBuilder {

    private const val BASE_URL = "https://api.stackexchange.com/2.2/"

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)
}