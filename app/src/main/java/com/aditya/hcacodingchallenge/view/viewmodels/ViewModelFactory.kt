package com.aditya.hcacodingchallenge.view.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aditya.hcacodingchallenge.network.ApiHelper
import javax.inject.Inject

/**
 * Class ViewModelFactory to create ViewModel instances
 */
class ViewModelFactory (private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(apiHelper) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}