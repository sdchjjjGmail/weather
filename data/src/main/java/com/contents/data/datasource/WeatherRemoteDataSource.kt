package com.contents.data.datasource

import com.contents.domain.model.CurrentWeather
import com.contents.domain.model.DailyWeather
import com.contents.domain.model.HourlyWeatherItem
import kotlinx.coroutines.flow.Flow

interface WeatherRemoteDataSource {
    suspend fun getCurrentWeather(locationKey: String, apiKey: String, language: String): Flow<CurrentWeather>
    suspend fun getHourlyWeather(locationKey: String, apiKey: String, language: String, metric: Boolean): Flow<List<HourlyWeatherItem>>
    suspend fun getDailyWeather(locationKey: String, apiKey: String, language: String, metric: Boolean): Flow<DailyWeather>
}