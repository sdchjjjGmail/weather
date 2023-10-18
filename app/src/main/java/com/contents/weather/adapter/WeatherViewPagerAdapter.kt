package com.contents.weather.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.contents.weather.fragment.CurrentWeatherFragment
import com.contents.weather.fragment.DailyWeatherFragment
import com.contents.weather.fragment.HourlyWeatherFragment

class WeatherViewPagerAdapter(
    fragmentActivity: FragmentActivity,
) : FragmentStateAdapter(fragmentActivity) {

    private val itemCount = 3

    override fun getItemCount(): Int {
        return itemCount
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun createFragment(position: Int): Fragment {
        return when (getItemId(position)) {
            0L -> CurrentWeatherFragment()
            1L -> HourlyWeatherFragment()
            2L -> DailyWeatherFragment()
            else -> CurrentWeatherFragment()
        }
    }
}