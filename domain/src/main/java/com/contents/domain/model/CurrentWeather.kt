package com.contents.domain.model

data class CurrentWeather(
    val epochTime: Long,
    val isDayTime: Boolean,
    val link: String,
    val localObservationDateTime: String,
    val mobileLink: String,
    val temperature: Temperature,
    val weatherIcon: Int,
    val weatherText: String
)

data class Temperature(
    val metric: Metric
)

data class Metric(
    val unit: String,
    val unitType: Int,
    val value: Double
)