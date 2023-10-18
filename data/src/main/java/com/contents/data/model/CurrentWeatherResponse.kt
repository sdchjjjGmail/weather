package com.contents.data.model

import com.google.gson.annotations.SerializedName


class CurrentWeatherResponse : ArrayList<CurrentWeatherResponseItem>()

data class CurrentWeatherResponseItem(
    @SerializedName("EpochTime")
    val epochTime: Long,
    @SerializedName("IsDayTime")
    val isDayTime: Boolean,
    @SerializedName("Link")
    val link: String,
    @SerializedName("LocalObservationDateTime")
    val localObservationDateTime: String,
    @SerializedName("MobileLink")
    val mobileLink: String,
    @SerializedName("Temperature")
    val temperature: TemperatureResponse,
    @SerializedName("WeatherIcon")
    val weatherIcon: Int,
    @SerializedName("WeatherText")
    val weatherText: String
)

data class TemperatureResponse(
    @SerializedName("Metric")
    val metric: MetricResponse
)

data class MetricResponse(
    @SerializedName("Unit")
    val unit: String,
    @SerializedName("UnitType")
    val unitType: Int,
    @SerializedName("Value")
    val value: Double
)