package com.contents.data.repository

import com.contents.data.datasource.PlaceLocalDataSource
import com.contents.data.datasource.PlaceRemoteDataSource
import com.contents.domain.model.GeocodeModel
import com.contents.domain.model.LocationKey
import com.contents.domain.model.PlaceModel
import com.contents.domain.model.UserLocation
import com.contents.domain.repository.PlaceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PlaceRepositoryImpl @Inject constructor(
    private val placeRemoteDataSource: PlaceRemoteDataSource,
    private val placeLocalDataSource: PlaceLocalDataSource
) : PlaceRepository {
    override suspend fun searchPlace(
        id: String,
        key: String,
        place: String
    ): Flow<PlaceModel> {
        return placeRemoteDataSource.searchPlace(id, key, place)
    }

    override suspend fun getCoordinates(
        id: String,
        key: String,
        query: String
    ): Flow<GeocodeModel> {
        return placeRemoteDataSource.getCoordinates(id, key, query)
    }

    override suspend fun getLocationKey(
        apikey: String,
        q: String,
        language: String
    ): Flow<LocationKey?> {
        return placeRemoteDataSource.getLocationKey(apikey, q, language)
    }

    override suspend fun getUserLocations(): Flow<List<UserLocation>> {
        return placeLocalDataSource.getUserLocations()
    }

    override suspend fun saveUserLocation(userLocation: UserLocation): Long {
        return placeLocalDataSource.saveUserLocation(userLocation)
    }

    override suspend fun deleteUserLocation(userLocation: UserLocation) {
        placeLocalDataSource.deleteUserLocation(userLocation)
    }
}