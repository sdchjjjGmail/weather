package com.contents.data.di

import com.contents.data.apiservice.CurrentWeatherService
import com.contents.data.apiservice.DailyWeatherService
import com.contents.data.apiservice.HourlyWeatherService
import com.contents.data.apiservice.LocationKeyService
import com.contents.data.apiservice.PlaceGeocodeService
import com.contents.data.apiservice.PlaceSearchService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApiModule {
    private const val PLACE_SEARCH_BASE_URL = "https://openapi.naver.com/v1/"
    private const val PLACE_GEOCODE_BASE_URL = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/"
    private const val PLACE_LOCATION_KEY_BASE_URL = "https://dataservice.accuweather.com/locations/v1/"
    private const val CURRENT_WEATHER_BASE_URL = "https://dataservice.accuweather.com/currentconditions/v1/"
    private const val FORECAST_WEATHER_BASE_URL = "https://dataservice.accuweather.com/forecasts/v1/"

    @Provides
    fun providePlaceSearchBaseUrl() = this.PLACE_SEARCH_BASE_URL
    @Provides
    fun providePlaceGeocodeBaseUrl() = this.PLACE_GEOCODE_BASE_URL
    @Provides
    fun provideLocationKeyBaseUrl() = this.PLACE_LOCATION_KEY_BASE_URL
    @Provides
    fun provideCurrentWeatherBaseUrl() = this.CURRENT_WEATHER_BASE_URL
    @Provides
    fun provideForecastWeatherBaseUrl() = this.FORECAST_WEATHER_BASE_URL

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class TypePlaceSearch
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class TypePlaceGeocode
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class TypeLocationKey
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class TypeCurrentWeather
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class TypeHourlyForecastWeather
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class TypeDailyForecastWeather

    @Singleton
    @Provides
    fun provideOkHttpClient() = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .apply {
            if (true) {
                addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
            }
        }.build()

    @TypePlaceSearch
    @Singleton
    @Provides
    fun providePlaceSearchService(okHttpClient: OkHttpClient): PlaceSearchService {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(providePlaceSearchBaseUrl())
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setLenient()
                        .create()
                )
            )
            .build()
            .create(PlaceSearchService::class.java)
    }

    @TypePlaceGeocode
    @Singleton
    @Provides
    fun providePlaceGeocodeService(okHttpClient: OkHttpClient): PlaceGeocodeService {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(providePlaceGeocodeBaseUrl())
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setLenient()
                        .create()
                )
            )
            .build()
            .create(PlaceGeocodeService::class.java)
    }

    @TypeLocationKey
    @Singleton
    @Provides
    fun provideLocationKeyService(okHttpClient: OkHttpClient): LocationKeyService {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(provideLocationKeyBaseUrl())
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setLenient()
                        .create()
                )
            )
            .build()
            .create(LocationKeyService::class.java)
    }

    @TypeCurrentWeather
    @Singleton
    @Provides
    fun provideCurrentWeatherService(okHttpClient: OkHttpClient): CurrentWeatherService {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(provideCurrentWeatherBaseUrl())
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setLenient()
                        .create()
                )
            )
            .build()
            .create(CurrentWeatherService::class.java)
    }

    @TypeHourlyForecastWeather
    @Singleton
    @Provides
    fun provideHourlyForecastWeatherService(okHttpClient: OkHttpClient): HourlyWeatherService {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(provideForecastWeatherBaseUrl())
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setLenient()
                        .create()
                )
            )
            .build()
            .create(HourlyWeatherService::class.java)
    }

    @TypeDailyForecastWeather
    @Singleton
    @Provides
    fun provideDailyForecastWeatherService(okHttpClient: OkHttpClient): DailyWeatherService {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(provideForecastWeatherBaseUrl())
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setLenient()
                        .create()
                )
            )
            .build()
            .create(DailyWeatherService::class.java)
    }
}