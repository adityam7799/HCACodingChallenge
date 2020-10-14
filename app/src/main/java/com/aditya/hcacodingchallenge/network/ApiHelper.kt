package com.aditya.hcacodingchallenge.network

import javax.inject.Inject

/**
 * Helper Class in which we can add cache logic in future
 */
class ApiHelper @Inject constructor(private val apiService: ApiService) {
    suspend fun getQuestions(pageNumber: Int) = apiService.getQuestions(pageNumber, 100, "desc",
    "creation", "stackoverflow")
}