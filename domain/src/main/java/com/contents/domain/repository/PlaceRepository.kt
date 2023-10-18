package com.contents.domain.repository

import com.contents.domain.model.GeocodeModel
import com.contents.domain.model.LocationKey
import com.contents.domain.model.PlaceModel
import com.contents.domain.model.UserLocation
import kotlinx.coroutines.flow.Flow

interface PlaceRepository {
    suspend fun searchPlace(id: String, key: String, place: String): Flow<PlaceModel>
    suspend fun getCoordinates(id: String, key: String, query: String): Flow<GeocodeModel>
    suspend fun getLocationKey(apikey: String, q: String, language: String): Flow<LocationKey?>
    suspend fun getUserLocations(): Flow<List<UserLocation>>
    suspend fun saveUserLocation(userLocation: UserLocation): Long
    suspend fun deleteUserLocation(userLocation: UserLocation)
}