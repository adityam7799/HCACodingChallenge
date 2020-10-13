package com.aditya.hcacodingchallenge.data

import com.google.gson.annotations.SerializedName

data class QuestionsResponse(
    @SerializedName("items") val items: List<QuestionInfo>,
    @SerializedName("has_more") val has_more: Boolean,
    @SerializedName("quota_max") val quota_max: Int,
    @SerializedName("quota_remaining") val quota_remaining: Int
)

data class QuestionInfo(
    @SerializedName("tags") val tags : List<String>,
    @SerializedName("owner") val owner : Owner,
    @SerializedName("is_answered") val is_answered : Boolean,
    @SerializedName("view_count") val view_count : Int,
    @SerializedName( "accepted_answer_id") val accepted_answer_id: Int,
    @SerializedName("answer_count") val answer_count : Int,
    @SerializedName("score") val score : Int,
    @SerializedName("last_activity_date") val last_activity_date : Int,
    @SerializedName("creation_date") val creation_date : Int,
    @SerializedName("last_edit_date") val last_edit_date : Int,
    @SerializedName("question_id") val question_id : Int,
    @SerializedName("content_license") val content_license : String,
    @SerializedName("link") val link : String,
    @SerializedName("title") val title : String
)

data class Owner (
    @SerializedName("reputation") val reputation : Int,
    @SerializedName("user_id") val user_id : Int,
    @SerializedName("user_type") val user_type : String,
    @SerializedName("accept_rate") val accept_rate : Int,
    @SerializedName("profile_image") val profile_image : String,
    @SerializedName("display_name") val display_name : String,
    @SerializedName("link") val link : String
)