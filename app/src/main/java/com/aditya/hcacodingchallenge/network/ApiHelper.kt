package com.aditya.hcacodingchallenge.network

import javax.inject.Inject

/**
 * Helper Class in which we can add cache logic in future
 */
class ApiHelper @Inject constructor(private val apiService: ApiService) {
    suspend fun getQuestions(pageNumber: Int, pageSize: Int, order: String, sort: String, site: String)
            = apiService.getQuestions(pageNumber, pageSize, order, sort, site)
}