package com.aditya.hcacodingchallenge.network

import javax.inject.Inject

/**
 * Helper Class in which we can add cache logic in future
 */
class ApiHelper @Inject constructor(private val apiService: ApiService) {
    suspend fun getQuestions() = apiService.getQuestions(1, 100, "desc",
    "activity", "stackoverflow")
}