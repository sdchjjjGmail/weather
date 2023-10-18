package com.contents.data.datasource

import com.contents.domain.model.GeocodeModel
import com.contents.domain.model.LocationKey
import com.contents.domain.model.PlaceModel
import kotlinx.coroutines.flow.Flow

interface PlaceRemoteDataSource {
    suspend fun searchPlace(id: String, key: String, place: String): Flow<PlaceModel>
    suspend fun getCoordinates(id: String, key: String, query: String): Flow<GeocodeModel>
    suspend fun getLocationKey(apikey: String, q: String, language: String): Flow<LocationKey?>
}