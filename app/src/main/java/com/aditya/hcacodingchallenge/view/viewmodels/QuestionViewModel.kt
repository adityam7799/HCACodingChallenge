package com.aditya.hcacodingchallenge.view.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aditya.hcacodingchallenge.data.QuestionInfo
import com.aditya.hcacodingchallenge.util.Utils.Companion.getDate

/**
 * Class which holds ViewModel for the Recyclerview adapter
 */
class QuestionViewModel : ViewModel() {
    private var questionTitle = MutableLiveData<String>()
    private var createdBy = MutableLiveData<String>()
    private var questionURL = MutableLiveData<String>()
    private var questionCreationDate = MutableLiveData<Long>()
    var answersCount = MutableLiveData<Int>()
    var questionViews = MutableLiveData<Int>()

    // Method to bind the data to recyclerview element
    fun bind(question: QuestionInfo) {
        questionTitle.value = question.title
        createdBy.value = question.owner.display_name
        questionURL.value = question.link
        questionCreationDate.value = question.creation_date
        questionViews.value = question.view_count
        answersCount.value = question.answer_count
    }

    // Method to return the question title
    fun getQuestionTitle(): MutableLiveData<String> {
        return questionTitle
    }

    // Method to return question owner
    fun getCreatedBy(): MutableLiveData<String> {
        return createdBy
    }

    // Method to return question created date by converting milliseconds to string
    fun getCreatedDate(): String? {
        return questionCreationDate.value?.let { getDate(it, "MM/dd/yyyy") }
    }

    // Method to return the link of the question
    fun getQuestionURL(): MutableLiveData<String> {
        return questionURL
    }
}