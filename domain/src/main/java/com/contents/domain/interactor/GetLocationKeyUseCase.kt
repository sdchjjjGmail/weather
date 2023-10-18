package com.contents.domain.interactor

import com.contents.domain.model.LocationKey
import kotlinx.coroutines.flow.Flow

fun interface GetLocationKeyUseCase: suspend (String, String, String) -> Flow<LocationKey?>