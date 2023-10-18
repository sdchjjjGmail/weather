package com.contents.data.di

import android.content.Context
import androidx.room.Room
import com.contents.data.localdb.dao.UserLocationDao
import com.contents.data.localdb.database.UserLocationDatabase
import com.contents.data.localdb.utils.UserLocationConverter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Singleton
    @Provides
    fun provideUserLocationDatabase(
        @ApplicationContext context: Context
    ): UserLocationDatabase = Room
        .databaseBuilder(context, UserLocationDatabase::class.java, "userLocationEntity.db")
        .addTypeConverter(UserLocationConverter())
        .build()

    @Singleton
    @Provides
    fun provideUserLocationDao(userLocationDatabase: UserLocationDatabase): UserLocationDao =
        userLocationDatabase.userLocationDao()
}