package com.aditya.hcacodingchallenge.view.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditya.hcacodingchallenge.R
import com.aditya.hcacodingchallenge.data.QuestionInfo
import com.aditya.hcacodingchallenge.databinding.ActivityMainBinding
import com.aditya.hcacodingchallenge.util.MainApplication
import com.aditya.hcacodingchallenge.util.Status
import com.aditya.hcacodingchallenge.view.adapters.QuestionsListAdapter
import com.aditya.hcacodingchallenge.view.viewmodels.MainViewModel
import com.aditya.hcacodingchallenge.view.viewmodels.QuestionViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

/**
 * MainActivity to load the recyclerview with the requested stackoverflow content
 */
class MainActivity : AppCompatActivity(), QuestionsListAdapter.OnQuestionClickListener {
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModel: MainViewModel
    private var pageNumber = 1
    private val newFilteredList: ArrayList<QuestionInfo> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {

        (applicationContext as MainApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpDataBinding()

        // Gets question list from API call
        questionsListObserver()

        binding.viewModel = viewModel
    }

    /**
     * Method to initialize and load databinding
     */
    private fun setUpDataBinding(){
        // Setup databinding for the layout
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.recylerQuestion.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL, false
        )
        binding.recylerQuestion.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.HORIZONTAL
            )
        )
    }

    /**
     * Method to initialize API call from ViewModel and listen to the data retrieved
     */
    private fun questionsListObserver() {

        viewModel.getQuestionsList(pageNumber).observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    recylerQuestion.visibility = View.VISIBLE
                    it.data?.let { questionsList ->
                        newFilteredList.addAll(viewModel.getFilteredList(questionsList))
                        if (newFilteredList.size < 50) {
                            pageNumber++
                            questionsListObserver()
                        } else {
                            progressBar.visibility = View.GONE
                            viewModel.onRetrieveQuestionsListSuccess(
                                newFilteredList,
                                this
                            )
                        }
                    }
                }
                Status.ERROR -> {
                    Snackbar.make(
                        binding.root, R.string.error_generic,
                        Snackbar.LENGTH_INDEFINITE
                    )
                    recylerQuestion.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    recylerQuestion.visibility = View.GONE
                }
            }

        })
    }

    /**
     * Listener which triggers when clicked on a question
     */
    override fun onQuestionClick(questionInfo: QuestionViewModel) {
        val intent = Intent(applicationContext, WebViewActivity::class.java)
        intent.putExtra("WEB_URL", questionInfo.getQuestionURL().value)
        startActivity(intent)
    }
}