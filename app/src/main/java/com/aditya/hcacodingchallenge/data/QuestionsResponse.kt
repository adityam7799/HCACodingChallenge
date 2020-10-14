package com.aditya.hcacodingchallenge.data

/**
 * Data class to hold the response returned from Stackoverflow API
 */
data class QuestionsResponse(
    val items: ArrayList<QuestionInfo>,
    val has_more: Boolean,
    val quota_max: Int,
    val quota_remaining: Int
)

/**
 * Data class to hold information about a question
 */
data class QuestionInfo(
    val tags : List<String>,
    val owner : Owner,
    val is_answered : Boolean,
    val view_count : Int,
    val accepted_answer_id: Int,
    val answer_count : Int,
    val score : Int,
    val last_activity_date : Int,
    val creation_date : Long,
    val last_edit_date : Int,
    val question_id : Int,
    val content_license : String,
    val link : String,
    val title : String
)

/**
 * Data class to hold information about the owner of the question
 */
data class Owner (
    val reputation : Int,
    val user_id : Int,
    val user_type : String,
    val accept_rate : Int,
    val profile_image : String,
    val display_name : String,
    val link : String
)