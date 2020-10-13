package com.aditya.hcacodingchallenge.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.MediaController
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditya.hcacodingchallenge.R
import com.aditya.hcacodingchallenge.databinding.ActivityMainBinding
import com.aditya.hcacodingchallenge.network.ApiHelper
import com.aditya.hcacodingchallenge.network.RetrofitBuilder
import com.aditya.hcacodingchallenge.util.MainApplication
import com.aditya.hcacodingchallenge.util.Status
import com.aditya.hcacodingchallenge.view.adapters.QuestionsListAdapter
import com.aditya.hcacodingchallenge.view.viewmodels.MainViewModel
import com.aditya.hcacodingchallenge.view.viewmodels.QuestionViewModel
import com.aditya.hcacodingchallenge.view.viewmodels.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), QuestionsListAdapter.OnQuestionClickListener {
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        (applicationContext as MainApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //setupViewModel()

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
        // Gets question list from API call
        questionsListObserver()

        binding.viewModel = viewModel
    }

    // Method to setup ViewModel for this activity
    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder().getRetrofit()))
        ).get(MainViewModel::class.java)
    }

    // Method to initialize API call from ViewModel and listen to the data retrieved
    private fun questionsListObserver() {

        viewModel.getQuestionsList().observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    recylerQuestion.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                    it.data?.let { questionsList ->
                        viewModel.onRetrieveQuestionsListSuccess(
                            questionsList,
                            this
                        )
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


    override fun onQuestionClick(questionInfo: QuestionViewModel) {
        Toast.makeText(this, "Question Clicked", Toast.LENGTH_LONG).show()
    }
}