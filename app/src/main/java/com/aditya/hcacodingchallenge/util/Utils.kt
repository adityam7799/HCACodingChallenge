package com.aditya.hcacodingchallenge.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

/**
 * Utils class to maintain reusable methods used across the application
 */
class Utils {

    companion object {
        /**
         * Method to format the date send in milliseconds to a sepecified format
         */
        fun getDate(milliSeconds: Long, dateFormat: String?): String? {
            // Create a DateFormatter object for displaying date in specified format.
            val date = Date(milliSeconds * 1000)
            val sdf = SimpleDateFormat(dateFormat, Locale.US)
            return sdf.format(date)
        }

        /**
         * Method to check if the mobile is connected to internet or not
         */
        fun isInternetConnected(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                        return true
                    }
                }
            }
            return false
        }

    }
}