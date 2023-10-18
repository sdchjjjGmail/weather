package com.contents.data.mapper

import com.contents.data.model.UserLocationEntity
import com.contents.domain.model.UserLocation

fun UserLocationEntity.toDomain(): UserLocation {
    return UserLocation(
        id = id,
        locationKey = locationKey,
        latitude = latitude,
        longitude = longitude
    )
}

fun UserLocation.toEntity(): UserLocationEntity {
    return UserLocationEntity(
        id = id,
        locationKey = locationKey,
        latitude = latitude,
        longitude = longitude
    )
}