package com.contents.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class PlaceModel(
    val display: Int,
    val items: List<Place>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)

@Parcelize
data class Place(
    val address: String,
    val category: String,
    val description: String,
    val link: String,
    val mapx: String,
    val mapy: String,
    val roadAddress: String,
    val telephone: String,
    val title: String
) : Parcelable

data class GeocodeModel(
    val status: String,
    val x: String,
    val y: String,
)