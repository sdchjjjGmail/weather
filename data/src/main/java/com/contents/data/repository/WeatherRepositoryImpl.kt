package com.contents.data.repository

import com.contents.data.datasource.WeatherRemoteDataSource
import com.contents.domain.model.CurrentWeather
import com.contents.domain.model.DailyWeather
import com.contents.domain.model.HourlyWeatherItem
import com.contents.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherRemoteDataSource: WeatherRemoteDataSource
) : WeatherRepository {
    override suspend fun getCurrentWeather(
        locationKey: String,
        apiKey: String,
        language: String
    ): Flow<CurrentWeather> {
        return weatherRemoteDataSource.getCurrentWeather(locationKey, apiKey, language)
    }

    override suspend fun getHourlyWeather(
        locationKey: String,
        apiKey: String,
        language: String,
        metric: Boolean
    ): Flow<List<HourlyWeatherItem>> {
        return weatherRemoteDataSource.getHourlyWeather(locationKey, apiKey, language, metric)
    }

    override suspend fun getDailyWeather(
        locationKey: String,
        apiKey: String,
        language: String,
        metric: Boolean
    ): Flow<DailyWeather> {
        return weatherRemoteDataSource.getDailyWeather(locationKey, apiKey, language, metric)
    }
}