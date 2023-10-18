package com.contents.data.mapper

import com.contents.data.model.GeoPositionResponse
import com.contents.data.model.GeocodeResponse
import com.contents.data.model.LocationKeyResponse
import com.contents.data.model.PlaceResponse
import com.contents.data.model.PlaceSearchResponse
import com.contents.domain.model.GeoPosition
import com.contents.domain.model.GeocodeModel
import com.contents.domain.model.LocationKey
import com.contents.domain.model.Place
import com.contents.domain.model.PlaceModel

fun PlaceSearchResponse.toDomain(): PlaceModel {
    return PlaceModel(
        display = display,
        items = items.map { it.toDomain() },
        lastBuildDate = lastBuildDate,
        start = start,
        total = total
    )
}

fun PlaceResponse.toDomain(): Place {
    return Place(
        address = address,
        category = category,
        description = description,
        link = link,
        mapx = mapx,
        mapy = mapy,
        roadAddress = roadAddress,
        telephone = telephone,
        title = refinePlaceTitle(title),
    )
}

private fun refinePlaceTitle(title: String): String {
    return with(title) {
        if (this@with.contains("<b>")) {
            replace("<b>", "")
                .replace("</b>", "")

        } else {
            this@with
        }
    }
}

fun GeocodeResponse.toDomain(): GeocodeModel {
    return GeocodeModel(
        status = status,
        x = addresses[0].x,
        y = addresses[0].y,
    )
}

fun LocationKeyResponse?.toDomain(): LocationKey? {
    if (this == null) {
        return null
    }
    return LocationKey(
        englishName = englishName,
        geoPosition = geoPosition.toDomain(),
        key = key,
        localizedName = localizedName,
        version = version
    )
}

private fun GeoPositionResponse.toDomain(): GeoPosition {
    return GeoPosition(
        latitude = latitude,
        longitude = longitude
    )
}