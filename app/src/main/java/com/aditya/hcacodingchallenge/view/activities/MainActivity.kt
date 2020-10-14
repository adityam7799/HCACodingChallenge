package com.aditya.hcacodingchallenge.view.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditya.hcacodingchallenge.R
import com.aditya.hcacodingchallenge.data.QuestionInfo
import com.aditya.hcacodingchallenge.databinding.ActivityMainBinding
import com.aditya.hcacodingchallenge.util.AppConstants.Companion.WEB_VIEW_ACTIVITY
import com.aditya.hcacodingchallenge.util.MainApplication
import com.aditya.hcacodingchallenge.util.Status
import com.aditya.hcacodingchallenge.util.Utils.Companion.isInternetConnected
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
        getQuestionsData()
    }

    /**
     * Method to initialize and load databinding
     */
    private fun setUpDataBinding() {
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
        binding.viewModel = viewModel
    }

    /**
     * Check for internet connection and listen to API response
     */
    private fun getQuestionsData() {
        if (isInternetConnected(this)) {
            questionsListObserver()
        } else {
            showNetworkAlert()
        }
    }

    /**
     * Show Network Alert Dialog
     */
    private fun showNetworkAlert() {
        AlertDialog.Builder(this).setTitle(R.string.title_no_internet)
            .setMessage(R.string.message_no_internet)
            .setPositiveButton(R.string.button_retry) { _, _ ->
                getQuestionsData()
            }
            .show()
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

                        // Making multiple api calls using pagination to get the list count of 50
                        if (newFilteredList.size < 50) {
                            pageNumber++
                            questionsListObserver()
                        } else {
                            progressBar.visibility = View.GONE
                            viewModel.notifyQuestionsToAdapter(
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
        if (isInternetConnected(this)) {
            val intent = Intent(applicationContext, WebViewActivity::class.java)
            intent.putExtra(WEB_VIEW_ACTIVITY, questionInfo.getQuestionURL().value)
            startActivity(intent)
        } else {
            showNetworkAlert()
        }
    }
}