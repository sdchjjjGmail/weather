package com.contents.weather.utils

import android.content.Context
import com.contents.weather.R

fun getViewPagerTitle(context: Context, position: Int): String {
    return with(
        listOf(
            context.getString(R.string.current_weather),
            context.getString(R.string.daily_weather),
            context.getString(R.string.weekly_weather),
        )
    ) {
        get(position)
    }
}