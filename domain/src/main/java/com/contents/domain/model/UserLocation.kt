package com.contents.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class UserLocation(
    val id: Long,
    val locationKey: LocationKey,
    val latitude: Double,
    val longitude: Double,
) : Parcelable
