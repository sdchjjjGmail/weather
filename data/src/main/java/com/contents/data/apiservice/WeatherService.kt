package com.contents.data.apiservice

import com.contents.data.model.CurrentWeatherResponse
import com.contents.data.model.HourlyWeatherResponse
import com.contents.data.model.DailyWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CurrentWeatherService {
    @GET("{locationKey}")
    suspend fun getCurrentWeather(
        @Path("locationKey") locationKey: String,
        @Query("apikey") apikey: String,
        @Query("language") language: String
    ): CurrentWeatherResponse
}

interface HourlyWeatherService {
    @GET("hourly/12hour/{locationKey}")
    suspend fun getHourlyWeather(
        @Path("locationKey") locationKey: String,
        @Query("apikey") apikey: String,
        @Query("language") language: String,
        @Query("metric") metric: Boolean,
    ): HourlyWeatherResponse
}

interface DailyWeatherService {
    @GET("daily/5day/{locationKey}")
    suspend fun getDailyWeather(
        @Path("locationKey") locationKey: String,
        @Query("apikey") apikey: String,
        @Query("language") language: String,
        @Query("metric") metric: Boolean,
    ): DailyWeatherResponse
}