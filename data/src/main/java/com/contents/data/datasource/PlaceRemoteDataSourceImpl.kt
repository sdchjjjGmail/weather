package com.contents.data.datasource

import com.contents.data.apiservice.LocationKeyService
import com.contents.data.apiservice.PlaceGeocodeService
import com.contents.data.apiservice.PlaceSearchService
import com.contents.data.di.ApiModule
import com.contents.data.mapper.toDomain
import com.contents.domain.model.GeocodeModel
import com.contents.domain.model.LocationKey
import com.contents.domain.model.PlaceModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlaceRemoteDataSourceImpl @Inject constructor(
    @ApiModule.TypePlaceSearch
    private val placeSearchService: PlaceSearchService,
    @ApiModule.TypePlaceGeocode
    private val placeGeocodeService: PlaceGeocodeService,
    @ApiModule.TypeLocationKey
    private val locationKeyService: LocationKeyService
) : PlaceRemoteDataSource {
    override suspend fun searchPlace(
        id: String,
        key: String,
        place: String
    ): Flow<PlaceModel> = flow {
        emit(
            placeSearchService.searchPlace(id, key, place).toDomain()
        )
    }

    override suspend fun getCoordinates(
        id: String,
        key: String,
        query: String
    ): Flow<GeocodeModel> = flow {
        emit(
            placeGeocodeService.getCoordinates(id, key, query).toDomain()
        )
    }

    override suspend fun getLocationKey(
        apikey: String,
        q: String,
        language: String
    ): Flow<LocationKey?> = flow {
        emit(
            locationKeyService.getLocationKey(apikey, q, language).body().toDomain()
        )
    }
}