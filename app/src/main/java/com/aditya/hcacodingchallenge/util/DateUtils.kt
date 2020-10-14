package com.aditya.hcacodingchallenge.util

import java.text.SimpleDateFormat
import java.util.*

class DateUtils {
    /**
     * Return date in specified format.
     * @param milliSeconds Date in milliseconds
     * @param dateFormat Date format
     * @return String representing date in specified format
     */
    companion object {
        fun getDate(milliSeconds: Long, dateFormat: String?): String? {
            // Create a DateFormatter object for displaying date in specified format.
            val date = Date(milliSeconds * 1000)
            val sdf = SimpleDateFormat(dateFormat, Locale.US)
            return sdf.format(date)
        }
    }
}