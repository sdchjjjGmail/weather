package com.contents.data.localdb.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.contents.data.localdb.dao.UserLocationDao
import com.contents.data.localdb.utils.UserLocationConverter
import com.contents.data.model.UserLocationEntity


@Database(entities = [UserLocationEntity::class], version = 1, exportSchema = false)
@TypeConverters(UserLocationConverter::class)
abstract class UserLocationDatabase : RoomDatabase() {
    abstract fun userLocationDao(): UserLocationDao
}