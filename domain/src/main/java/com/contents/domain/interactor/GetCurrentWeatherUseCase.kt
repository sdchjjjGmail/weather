package com.contents.domain.interactor

import com.contents.domain.model.CurrentWeather
import kotlinx.coroutines.flow.Flow

fun interface GetCurrentWeatherUseCase: suspend (String, String, String) -> Flow<CurrentWeather>