package com.aditya.hcacodingchallenge.util

import android.app.Application
import com.aditya.hcacodingchallenge.dagger.ApplicationComponent
import com.aditya.hcacodingchallenge.dagger.DaggerApplicationComponent
import dagger.android.support.DaggerApplication

class MainApplication : Application() {
    // Reference to the application graph that is used across the whole app
    val appComponent: ApplicationComponent = DaggerApplicationComponent.create()
}