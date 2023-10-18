package com.contents.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class LocationKey(
    val englishName: String = "",
    val geoPosition: GeoPosition,
    val key: String = "",
    val localizedName: String = "",
    val version: Int = 0
) : Parcelable

@Parcelize
data class GeoPosition(
    // y
    val latitude: Double = 0.0,
    // x
    val longitude: Double = 0.0
) : Parcelable
