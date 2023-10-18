package com.contents.data.datasource

import com.contents.data.localdb.dao.UserLocationDao
import com.contents.data.mapper.toDomain
import com.contents.data.mapper.toEntity
import com.contents.domain.model.UserLocation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class PlaceLocalDataSourceImpl @Inject constructor(
    private val userLocationDao: UserLocationDao
) : PlaceLocalDataSource {
    override fun getUserLocations(): Flow<List<UserLocation>> {
        return userLocationDao.getUserLocations().map { dataList ->
            dataList.map { singleData ->
                singleData.toDomain()
            }
        }
    }

    override fun saveUserLocation(userLocation: UserLocation): Long {
        return userLocationDao.insertUserLocation(userLocation.toEntity())
    }

    override fun deleteUserLocation(userLocation: UserLocation) {
        userLocationDao.deleteUserLocation(userLocation.toEntity())
    }
}