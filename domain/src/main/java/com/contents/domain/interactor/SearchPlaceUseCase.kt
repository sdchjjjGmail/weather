package com.contents.domain.interactor

import com.contents.domain.model.PlaceModel
import kotlinx.coroutines.flow.Flow

fun interface SearchPlaceUseCase : suspend (String, String, String) -> Flow<PlaceModel>
