package com.aditya.hcacodingchallenge.dagger

import com.aditya.hcacodingchallenge.network.RetrofitBuilder
import com.aditya.hcacodingchallenge.view.activities.MainActivity
import dagger.Component

@Component(modules = [RetrofitBuilder::class])
interface ApplicationComponent {
    // This tells Dagger that MainActivity requests injection so the graph needs to
    // satisfy all the dependencies of the fields that MainActivity is requesting.
    fun inject(activity: MainActivity)
}