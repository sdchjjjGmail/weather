package com.contents.weather.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.contents.domain.model.DailyForecast
import com.contents.domain.model.HourlyWeatherItem
import com.contents.weather.adapter.DailyWeatherAdapter
import com.contents.weather.adapter.HourlyWeatherAdapter

object HourlyWeatherBindingAdapter {
    @BindingAdapter("hourlyWeatherItems")
    @JvmStatic
    fun setHourlyWeatherItems(recyclerView: RecyclerView, items: List<HourlyWeatherItem>?) {
        val adapter = recyclerView.adapter as HourlyWeatherAdapter
        if (items != null) {
            adapter.submitList(items.toMutableList())
        }
    }
}

object DailyWeatherBindingAdapter {
    @BindingAdapter("dailyWeatherItems")
    @JvmStatic
    fun setDailyWeatherItems(recyclerView: RecyclerView, items: List<DailyForecast>?) {
        val adapter = recyclerView.adapter as DailyWeatherAdapter
        if (items != null) {
            adapter.submitList(items.toMutableList())
        }
    }
}

object WeatherIconBindingAdapter {
    @BindingAdapter("weatherIcon")
    @JvmStatic
    fun setWeatherIcon(view: ImageView, iconNumber: Int) {
        view.setImageResource(WeatherIconProvider.getIcon(iconNumber))
    }
}