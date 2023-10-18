package com.contents.data.localdb.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.contents.data.model.UserLocationEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface UserLocationDao {
    @Query("SELECT * FROM UserLocationEntity")
    fun getUserLocations(): Flow<List<UserLocationEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserLocation(userLocationEntity: UserLocationEntity): Long

    @Delete
    fun deleteUserLocation(userLocationEntity: UserLocationEntity)
}