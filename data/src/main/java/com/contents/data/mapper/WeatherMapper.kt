package com.contents.data.mapper

import com.contents.data.model.CurrentWeatherResponse
import com.contents.data.model.DailyForecastResponse
import com.contents.data.model.DailyTemperatureResponse
import com.contents.data.model.DailyWeatherResponse
import com.contents.data.model.DayResponse
import com.contents.data.model.HeadlineResponse
import com.contents.data.model.HourlyTemperatureResponse
import com.contents.data.model.HourlyWeatherResponseItem
import com.contents.data.model.MaximumResponse
import com.contents.data.model.MetricResponse
import com.contents.data.model.MinimumResponse
import com.contents.data.model.NightResponse
import com.contents.data.model.TemperatureResponse
import com.contents.domain.model.CurrentWeather
import com.contents.domain.model.DailyForecast
import com.contents.domain.model.DailyTemperature
import com.contents.domain.model.DailyWeather
import com.contents.domain.model.Day
import com.contents.domain.model.Headline
import com.contents.domain.model.HourlyTemperature
import com.contents.domain.model.HourlyWeatherItem
import com.contents.domain.model.Maximum
import com.contents.domain.model.Metric
import com.contents.domain.model.Minimum
import com.contents.domain.model.Night
import com.contents.domain.model.Temperature

fun CurrentWeatherResponse.toDomain(): CurrentWeather {
    with(this[0]) {
        return CurrentWeather(
            epochTime = epochTime,
            isDayTime = isDayTime,
            link = link,
            localObservationDateTime = localObservationDateTime,
            mobileLink = mobileLink,
            temperature = temperature.toDomain(),
            weatherIcon = weatherIcon,
            weatherText = weatherText,
        )
    }
}

private fun TemperatureResponse.toDomain(): Temperature {
    return Temperature(
        metric = metric.toDomain()
    )
}

private fun MetricResponse.toDomain(): Metric {
    return Metric(
        unit = unit,
        unitType = unitType,
        value = value
    )
}

fun HourlyWeatherResponseItem.toDomain(): HourlyWeatherItem {
    return HourlyWeatherItem(
        dateTime = dateTime,
        epochDateTime = epochDateTime,
        hasPrecipitation = hasPrecipitation,
        iconPhrase = iconPhrase,
        isDaylight = isDaylight,
        link = link,
        mobileLink = mobileLink,
        precipitationProbability = precipitationProbability,
        temperature = temperature!!.toDomain(),
        weatherIcon = weatherIcon
    )
}

private fun HourlyTemperatureResponse.toDomain(): HourlyTemperature {
    return HourlyTemperature(
        unit = unit,
        unitType = unitType,
        value = value
    )
}

fun DailyWeatherResponse.toDomain(): DailyWeather {
    return DailyWeather(
        dailyForecast = dailyForecastResponses!!.map {
            it!!.toDomain()
        },
        headline = headlineResponse!!.toDomain()
    )
}

private fun HeadlineResponse.toDomain(): Headline {
    return Headline(
        category = category,
        effectiveDate = effectiveDate,
        effectiveEpochDate = effectiveEpochDate,
        endDate = endDate,
        endEpochDate = endEpochDate,
        link = link,
        mobileLink = mobileLink,
        severity = severity,
        text = text
    )
}

private fun DailyForecastResponse.toDomain(): DailyForecast {
    return DailyForecast(
        date = date,
        day = dayResponse!!.toDomain(),
        epochDate = epochDate,
        link = link,
        mobileLink = mobileLink,
        night = nightResponse!!.toDomain(),
//        sources = sources,
        dailyTemperature = dailyTemperatureResponse!!.toDomain()
    )
}

private fun DayResponse.toDomain(): Day {
    return Day(
        hasPrecipitation = hasPrecipitation,
        icon = icon,
        iconPhrase = iconPhrase,
        precipitationIntensity = precipitationIntensity,
        precipitationType = precipitationType,
    )
}

private fun NightResponse.toDomain(): Night {
    return Night(
        hasPrecipitation = hasPrecipitation,
        icon = icon,
        iconPhrase = iconPhrase,
        precipitationIntensity = precipitationIntensity,
        precipitationType = precipitationType,
    )
}

private fun DailyTemperatureResponse.toDomain(): DailyTemperature {
    return DailyTemperature(
        maximum = maximumResponse!!.toDomain(),
        minimum = minimumResponse!!.toDomain()
    )
}

private fun MaximumResponse.toDomain(): Maximum {
    return Maximum(
        unit = unit,
        unitType = unitType,
        value = value
    )
}

private fun MinimumResponse.toDomain(): Minimum {
    return Minimum(
        unit = unit,
        unitType = unitType,
        value = value
    )
}

