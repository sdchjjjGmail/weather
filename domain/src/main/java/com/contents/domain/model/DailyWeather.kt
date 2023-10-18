package com.contents.domain.model

data class DailyWeather(
    val dailyForecast: List<DailyForecast?>?,
    val headline: Headline?
)

data class Headline(
    val category: String?,
    val effectiveDate: String?,
    val effectiveEpochDate: Int?,
    val endDate: String?,
    val endEpochDate: Int?,
    val link: String?,
    val mobileLink: String?,
    val severity: Int?,
    val text: String?
)

data class DailyForecast(
    val date: String?,
    val day: Day?,
    val epochDate: Long?,
    val link: String?,
    val mobileLink: String?,
    val night: Night?,
//    val sources: List<String?>?,
    val dailyTemperature: DailyTemperature?,
)

data class Day(
    val hasPrecipitation: Boolean?,
    val icon: Int?,
    val iconPhrase: String?,
    val precipitationIntensity: String?,
    val precipitationType: String?
)

data class Night(
    val hasPrecipitation: Boolean?,
    val icon: Int?,
    val iconPhrase: String?,
    val precipitationIntensity: String?,
    val precipitationType: String?
)

data class DailyTemperature(
    val maximum: Maximum?,
    val minimum: Minimum?
)

data class Maximum(
    val unit: String?,
    val unitType: Int?,
    val value: Double?
)

data class Minimum(
    val unit: String?,
    val unitType: Int?,
    val value: Double?
)