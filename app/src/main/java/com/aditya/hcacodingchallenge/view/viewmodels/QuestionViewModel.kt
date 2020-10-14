package com.aditya.hcacodingchallenge.view.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aditya.hcacodingchallenge.data.QuestionInfo
import com.aditya.hcacodingchallenge.util.DateUtils.Companion.getDate

/**
 * Class which holds ViewModel for the Recyclerview adapter
 */
class QuestionViewModel: ViewModel() {
    private var questionTitle =  MutableLiveData<String>()
    private var questionURL =  MutableLiveData<String>()
    private var questionCreationDate = MutableLiveData<Long>()
    var questionViews = MutableLiveData<Int>()

    fun bind(question: QuestionInfo){
        questionTitle.value = question.title
        questionURL.value = question.link
        questionCreationDate.value = question.creation_date
        questionViews.value = question.view_count
    }
    fun getQuestionTitle(): MutableLiveData<String> {
        return questionTitle
    }
    fun getCreatedDate(): String?{
        return questionCreationDate.value?.let { getDate(it, "dd/MM/yyyy") }
    }

    fun getQuestionURL(): MutableLiveData<String> {
        return questionURL
    }
}