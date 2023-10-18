package com.contents.data.model


import com.google.gson.annotations.SerializedName

data class DailyWeatherResponse(
    @SerializedName("DailyForecasts")
    val dailyForecastResponses: List<DailyForecastResponse?>?,
    @SerializedName("Headline")
    val headlineResponse: HeadlineResponse?
)

data class HeadlineResponse(
    @SerializedName("Category")
    val category: String?,
    @SerializedName("EffectiveDate")
    val effectiveDate: String?,
    @SerializedName("EffectiveEpochDate")
    val effectiveEpochDate: Int?,
    @SerializedName("EndDate")
    val endDate: String?,
    @SerializedName("EndEpochDate")
    val endEpochDate: Int?,
    @SerializedName("Link")
    val link: String?,
    @SerializedName("MobileLink")
    val mobileLink: String?,
    @SerializedName("Severity")
    val severity: Int?,
    @SerializedName("Text")
    val text: String?
)

data class DailyForecastResponse(
    @SerializedName("Date")
    val date: String?,
    @SerializedName("Day")
    val dayResponse: DayResponse?,
    @SerializedName("EpochDate")
    val epochDate: Long?,
    @SerializedName("Link")
    val link: String?,
    @SerializedName("MobileLink")
    val mobileLink: String?,
    @SerializedName("Night")
    val nightResponse: NightResponse?,
    @SerializedName("Sources")
    val sources: List<String?>?,
    @SerializedName("Temperature")
    val dailyTemperatureResponse: DailyTemperatureResponse?
)

data class DayResponse(
    @SerializedName("HasPrecipitation")
    val hasPrecipitation: Boolean?,
    @SerializedName("Icon")
    val icon: Int?,
    @SerializedName("IconPhrase")
    val iconPhrase: String?,
    @SerializedName("PrecipitationIntensity")
    val precipitationIntensity: String?,
    @SerializedName("PrecipitationType")
    val precipitationType: String?
)

data class NightResponse(
    @SerializedName("HasPrecipitation")
    val hasPrecipitation: Boolean?,
    @SerializedName("Icon")
    val icon: Int?,
    @SerializedName("IconPhrase")
    val iconPhrase: String?,
    @SerializedName("PrecipitationIntensity")
    val precipitationIntensity: String?,
    @SerializedName("PrecipitationType")
    val precipitationType: String?
)

data class DailyTemperatureResponse(
    @SerializedName("Maximum")
    val maximumResponse: MaximumResponse?,
    @SerializedName("Minimum")
    val minimumResponse: MinimumResponse?
)

data class MaximumResponse(
    @SerializedName("Unit")
    val unit: String?,
    @SerializedName("UnitType")
    val unitType: Int?,
    @SerializedName("Value")
    val value: Double?
)

data class MinimumResponse(
    @SerializedName("Unit")
    val unit: String?,
    @SerializedName("UnitType")
    val unitType: Int?,
    @SerializedName("Value")
    val value: Double?
)