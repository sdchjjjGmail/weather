package com.contents.data.model

import com.google.gson.annotations.SerializedName

data class LocationKeyResponse(
    @SerializedName("EnglishName")
    val englishName: String,
    @SerializedName("GeoPosition")
    val geoPosition: GeoPositionResponse,
    @SerializedName("Key")
    val key: String,
    @SerializedName("LocalizedName")
    val localizedName: String,
    @SerializedName("Version")
    val version: Int
)

data class GeoPositionResponse(
    @SerializedName("Latitude")
    val latitude: Double,
    @SerializedName("Longitude")
    val longitude: Double
)