package com.contents.data.apiservice

import com.contents.data.model.GeocodeResponse
import com.contents.data.model.LocationKeyResponse
import com.contents.data.model.PlaceSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface PlaceSearchService {
    @GET("search/local.json?")
    suspend fun searchPlace(
        @Header("X-Naver-Client-Id") id: String,
        @Header("X-Naver-Client-Secret") key: String,
        @Query("query") query: String,
        @Query("display") display: String = "20",
        @Query("start") start: String = "1",
        @Query("sort") sort: String = "random"
    ): PlaceSearchResponse
}

interface PlaceGeocodeService {
    @GET("geocode")
    suspend fun getCoordinates(
        @Header("X-NCP-APIGW-API-KEY-ID") id: String,
        @Header("X-NCP-APIGW-API-KEY") key: String,
        @Query("query") query: String
    ): GeocodeResponse
}

interface LocationKeyService {
    @GET("cities/geoposition/search")
    suspend fun getLocationKey(
        @Query("apikey") apiKey: String,
        @Query("q") q: String,
        @Query("language") lang: String
    ): Response<LocationKeyResponse?>
}