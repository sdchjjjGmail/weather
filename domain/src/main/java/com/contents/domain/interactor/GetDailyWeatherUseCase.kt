package com.contents.domain.interactor

import com.contents.domain.model.DailyWeather
import kotlinx.coroutines.flow.Flow

fun interface GetDailyWeatherUseCase : suspend (String, String, String, Boolean) -> Flow<DailyWeather>