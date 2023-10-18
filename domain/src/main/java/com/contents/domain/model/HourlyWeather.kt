package com.contents.domain.model

data class HourlyWeatherItem(
    val dateTime: String?,
    val epochDateTime: Long?,
    val hasPrecipitation: Boolean?,
    val iconPhrase: String?,
    val isDaylight: Boolean?,
    val link: String?,
    val mobileLink: String?,
    val precipitationProbability: Int?,
    val temperature: HourlyTemperature?,
    val weatherIcon: Int?
)

data class HourlyTemperature(
    val unit: String?,
    val unitType: Int?,
    val value: Double?
)