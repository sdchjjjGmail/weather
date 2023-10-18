package com.contents.domain.di

import com.contents.domain.interactor.DeleteUserLocationUseCase
import com.contents.domain.interactor.GetCoordinatesUseCase
import com.contents.domain.interactor.GetCurrentWeatherUseCase
import com.contents.domain.interactor.GetDailyWeatherUseCase
import com.contents.domain.interactor.GetHourlyWeatherUseCase
import com.contents.domain.interactor.GetLocationKeyUseCase
import com.contents.domain.interactor.GetUserLocationsUseCase
import com.contents.domain.interactor.SaveUserLocationUseCase
import com.contents.domain.interactor.SearchPlaceUseCase
import com.contents.domain.repository.PlaceRepository
import com.contents.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object UseCaseModule {
    @Provides
    @Singleton
    fun provideSearchPlaceUseCase(
        placeRepository: PlaceRepository
    ): SearchPlaceUseCase {
        return SearchPlaceUseCase(placeRepository::searchPlace)
    }

    @Provides
    @Singleton
    fun provideGetCoordinatesUseCase(
        placeRepository: PlaceRepository
    ): GetCoordinatesUseCase {
        return GetCoordinatesUseCase(placeRepository::getCoordinates)
    }

    @Provides
    @Singleton
    fun provideGetLocationKeyUseCase(
        placeRepository: PlaceRepository
    ): GetLocationKeyUseCase {
        return GetLocationKeyUseCase(placeRepository::getLocationKey)
    }

    @Provides
    @Singleton
    fun provideGetUserLocationsUseCase(
        placeRepository: PlaceRepository
    ): GetUserLocationsUseCase {
        return GetUserLocationsUseCase(placeRepository::getUserLocations)
    }

    @Provides
    @Singleton
    fun provideSaveUserLocationUseCase(
        placeRepository: PlaceRepository
    ): SaveUserLocationUseCase {
        return SaveUserLocationUseCase(placeRepository::saveUserLocation)
    }

    @Provides
    @Singleton
    fun provideDeleteUserLocationUseCase(
        placeRepository: PlaceRepository
    ): DeleteUserLocationUseCase {
        return DeleteUserLocationUseCase(placeRepository::deleteUserLocation)
    }

    @Provides
    @Singleton
    fun provideGetCurrentWeatherUseCase(
        weatherRepository: WeatherRepository
    ): GetCurrentWeatherUseCase {
        return GetCurrentWeatherUseCase(weatherRepository::getCurrentWeather)
    }

    @Provides
    @Singleton
    fun provideGetHourlyWeatherUseCase(
        weatherRepository: WeatherRepository
    ): GetHourlyWeatherUseCase {
        return GetHourlyWeatherUseCase(weatherRepository::getHourlyWeather)
    }

    @Provides
    @Singleton
    fun provideGetDailyWeatherUseCase(
        weatherRepository: WeatherRepository
    ): GetDailyWeatherUseCase {
        return GetDailyWeatherUseCase(weatherRepository::getDailyWeather)
    }
}