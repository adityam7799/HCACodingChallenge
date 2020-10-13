package com.aditya.hcacodingchallenge.view.viewmodels

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aditya.hcacodingchallenge.data.QuestionInfo

/**
 * Class which holds ViewModel for the Recyclerview adapter
 */
class QuestionViewModel: ViewModel() {
    private var questionTitle =  MutableLiveData<String>()
    private var questionURL =  MutableLiveData<String>()

    fun bind(question: QuestionInfo){
        questionTitle.value = question.title
    }
    fun getQuestionTitle(): MutableLiveData<String> {
        return questionTitle
    }
    fun getQuestionURL(): MutableLiveData<String> {
        return questionURL
    }
}