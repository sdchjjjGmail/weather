package com.contents.data.model

import com.google.gson.annotations.SerializedName


class HourlyWeatherResponse : ArrayList<HourlyWeatherResponseItem>()

data class HourlyWeatherResponseItem(
    @SerializedName("DateTime")
    val dateTime: String?,
    @SerializedName("EpochDateTime")
    val epochDateTime: Long?,
    @SerializedName("HasPrecipitation")
    val hasPrecipitation: Boolean?,
    @SerializedName("IconPhrase")
    val iconPhrase: String?,
    @SerializedName("IsDaylight")
    val isDaylight: Boolean?,
    @SerializedName("Link")
    val link: String?,
    @SerializedName("MobileLink")
    val mobileLink: String?,
    @SerializedName("PrecipitationProbability")
    val precipitationProbability: Int?,
    @SerializedName("Temperature")
    val temperature: HourlyTemperatureResponse?,
    @SerializedName("WeatherIcon")
    val weatherIcon: Int?
)

data class HourlyTemperatureResponse(
    @SerializedName("Unit")
    val unit: String?,
    @SerializedName("UnitType")
    val unitType: Int?,
    @SerializedName("Value")
    val value: Double?
)