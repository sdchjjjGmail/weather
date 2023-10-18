package com.contents.data.datasource

import com.contents.data.apiservice.CurrentWeatherService
import com.contents.data.apiservice.DailyWeatherService
import com.contents.data.apiservice.HourlyWeatherService
import com.contents.data.di.ApiModule
import com.contents.data.mapper.toDomain
import com.contents.domain.model.CurrentWeather
import com.contents.domain.model.DailyWeather
import com.contents.domain.model.HourlyWeatherItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherRemoteDataSourceImpl @Inject constructor(
    @ApiModule.TypeCurrentWeather
    private val currentWeatherService: CurrentWeatherService,
    @ApiModule.TypeHourlyForecastWeather
    private val hourlyWeatherService: HourlyWeatherService,
    @ApiModule.TypeDailyForecastWeather
    private val dailyWeatherService: DailyWeatherService,
) : WeatherRemoteDataSource {
    override suspend fun getCurrentWeather(
        locationKey: String,
        apiKey: String,
        language: String
    ): Flow<CurrentWeather> = flow {
        emit(
            currentWeatherService.getCurrentWeather(locationKey, apiKey, language).toDomain()
        )
    }

    override suspend fun getHourlyWeather(
        locationKey: String,
        apiKey: String,
        language: String,
        metric: Boolean
    ): Flow<List<HourlyWeatherItem>> = flow {
        emit(
            hourlyWeatherService.getHourlyWeather(locationKey, apiKey, language, metric).map {
                it.toDomain()
            }
        )
    }

    override suspend fun getDailyWeather(
        locationKey: String,
        apiKey: String,
        language: String,
        metric: Boolean
    ): Flow<DailyWeather> = flow {
        emit(
            dailyWeatherService.getDailyWeather(locationKey, apiKey, language, metric).toDomain()
        )
    }
}