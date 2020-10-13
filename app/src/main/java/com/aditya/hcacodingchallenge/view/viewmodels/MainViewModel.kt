package com.aditya.hcacodingchallenge.view.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.aditya.hcacodingchallenge.data.QuestionInfo
import com.aditya.hcacodingchallenge.data.QuestionsResponse
import com.aditya.hcacodingchallenge.network.ApiHelper
import com.aditya.hcacodingchallenge.util.Resource
import com.aditya.hcacodingchallenge.view.adapters.QuestionsListAdapter
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class MainViewModel @Inject constructor (private val apiHelper: ApiHelper) : ViewModel() {
    val questionsListAdapter: QuestionsListAdapter = QuestionsListAdapter()

    // Method to fetch data from API
    fun getQuestionsList() = liveData(Dispatchers.IO) {

        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiHelper.getQuestions()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    // Method which is called when data is retrieved from Network call
    fun onRetrieveQuestionsListSuccess(
        apiResponse: QuestionsResponse,
        mListener: QuestionsListAdapter.OnQuestionClickListener
    ) {
        val newFilteredList = getFilteredList(apiResponse)
        questionsListAdapter.updateQuestionsList(newFilteredList, mListener)
        Log.w("class", apiResponse.toString())
    }

    private fun getFilteredList(apiResponse: QuestionsResponse): ArrayList<QuestionInfo> {
        val newFilteredList: ArrayList<QuestionInfo> = arrayListOf()
        apiResponse.items.forEach {
            if (it.answer_count > 0 && it.accepted_answer_id != 0) {
                newFilteredList.add(it)
            }
        }
        return newFilteredList
    }
}