package com.aditya.hcacodingchallenge.network

import com.aditya.hcacodingchallenge.data.QuestionsResponse
import retrofit2.http.GET

/**
 * interface to maintain all the Network APIs
 */
interface ApiService {

    // GET network API call to get data from the API
    @GET("questions?order=desc&sort=activity&site=stackoverflow")
    suspend fun getQuestions(): List<QuestionsResponse>

}