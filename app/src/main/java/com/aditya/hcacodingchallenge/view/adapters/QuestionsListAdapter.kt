package com.aditya.hcacodingchallenge.view.adapters

import android.graphics.Bitmap
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.aditya.hcacodingchallenge.R
import com.aditya.hcacodingchallenge.data.QuestionInfo
import com.aditya.hcacodingchallenge.data.QuestionsResponse
import com.aditya.hcacodingchallenge.databinding.ItemQuestionsBinding
import com.aditya.hcacodingchallenge.view.viewmodels.QuestionViewModel

class QuestionsListAdapter: RecyclerView.Adapter<QuestionsListAdapter.ViewHolder>() {

    private lateinit var mListener: OnQuestionClickListener
    private lateinit var questionsList:List<QuestionInfo>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemQuestionsBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_questions,
                parent,
                false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(questionsList[position], mListener)
    }

    override fun getItemCount(): Int {
        return if(::questionsList.isInitialized) questionsList.size else 0
    }

    // Method to update data retrived from API to recyclerview
    fun updateQuestionsList(questionsList:ArrayList<QuestionInfo>, mListener: OnQuestionClickListener){
        this.questionsList = questionsList
        this.mListener = mListener
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemQuestionsBinding): RecyclerView.ViewHolder(binding.root){
        private val viewModel = QuestionViewModel()

        fun bind(questionInfo:QuestionInfo, listener: OnQuestionClickListener){
            viewModel.bind(questionInfo)
            binding.itemClick = listener
            binding.viewModel = viewModel
        }
    }

    /**
     * Interface to add onClick Listener for the item in recyclerview
     */
    interface OnQuestionClickListener{
        fun onQuestionClick(questionInfo: QuestionViewModel)
    }
}
