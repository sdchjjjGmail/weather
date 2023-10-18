package com.contents.domain.interactor

import com.contents.domain.model.HourlyWeatherItem
import kotlinx.coroutines.flow.Flow

fun interface GetHourlyWeatherUseCase : suspend (String, String, String, Boolean) -> Flow<List<HourlyWeatherItem>>