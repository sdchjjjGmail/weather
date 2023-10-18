package com.contents.data.di

import com.contents.data.datasource.PlaceLocalDataSource
import com.contents.data.datasource.PlaceLocalDataSourceImpl
import com.contents.data.datasource.PlaceRemoteDataSource
import com.contents.data.datasource.PlaceRemoteDataSourceImpl
import com.contents.data.datasource.WeatherRemoteDataSource
import com.contents.data.datasource.WeatherRemoteDataSourceImpl
import com.contents.data.repository.PlaceRepositoryImpl
import com.contents.data.repository.WeatherRepositoryImpl
import com.contents.domain.repository.PlaceRepository
import com.contents.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DataSourceModule {
    @Binds
    abstract fun providePlaceRepository(
        placeRepositoryImpl: PlaceRepositoryImpl
    ): PlaceRepository

    @Binds
    abstract fun providePlaceRemoteDataSource(
        placeRemoteDataSourceImpl: PlaceRemoteDataSourceImpl
    ): PlaceRemoteDataSource

    @Binds
    abstract fun providePlaceLocalDataSource(
        placeLocalDataSourceImpl: PlaceLocalDataSourceImpl
    ): PlaceLocalDataSource

    @Binds
    abstract fun provideWeatherRepository(
        weatherRepositoryImpl: WeatherRepositoryImpl
    ): WeatherRepository

    @Binds
    abstract fun provideWeatherRemoteDataSource(
        weatherRemoteDataSourceImpl: WeatherRemoteDataSourceImpl
    ): WeatherRemoteDataSource
}