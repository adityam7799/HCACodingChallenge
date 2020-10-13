package com.aditya.hcacodingchallenge.network

/**
 * Helper Class in which we can add cache logic in future
 */
class ApiHelper(private val apiService: ApiService) {
    suspend fun getQuestions() = apiService.getQuestions()
}