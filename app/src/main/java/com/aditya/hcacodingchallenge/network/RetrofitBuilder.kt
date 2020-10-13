package com.aditya.hcacodingchallenge.network

import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Class for configuring retrofit for network calls.
 */
@Module
class RetrofitBuilder {

    private val BASE_URL = "https://api.stackexchange.com/2.2/"

    @Provides
    fun getRetrofit(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

}