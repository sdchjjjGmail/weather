package com.contents.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.contents.domain.model.LocationKey


@Entity
data class UserLocationEntity(
    @PrimaryKey
    val id: Long,
    @ColumnInfo val locationKey: LocationKey,
    @ColumnInfo val latitude: Double,
    @ColumnInfo val longitude: Double,
)