package com.contents.data.model

data class PlaceSearchResponse(
    val display: Int,
    val items: List<PlaceResponse>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)

data class PlaceResponse(
    val address: String,
    val category: String,
    val description: String,
    val link: String,
    val mapx: String,
    val mapy: String,
    val roadAddress: String,
    val telephone: String,
    val title: String
)