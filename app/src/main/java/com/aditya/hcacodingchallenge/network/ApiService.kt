package com.aditya.hcacodingchallenge.network

import com.aditya.hcacodingchallenge.data.QuestionsResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * interface to maintain all the Network APIs
 */
interface ApiService {
    // GET network API call to get data from the API
    @GET("questions")
    suspend fun getQuestions(
        @Query("page") page: Int,
        @Query("pagesize") pagesize: Int,
        @Query("order") order: String,
        @Query("sort") sort: String,
        @Query("site") site: String
    ): QuestionsResponse

}