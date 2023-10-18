package com.contents.data.datasource

import com.contents.domain.model.UserLocation
import kotlinx.coroutines.flow.Flow

interface PlaceLocalDataSource {
    fun getUserLocations(): Flow<List<UserLocation>>
    fun saveUserLocation(userLocation: UserLocation): Long
    fun deleteUserLocation(userLocation: UserLocation)
}