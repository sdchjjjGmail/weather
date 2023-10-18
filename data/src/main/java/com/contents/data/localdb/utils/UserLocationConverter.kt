package com.contents.data.localdb.utils

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.contents.domain.model.LocationKey
import com.google.gson.Gson


@ProvidedTypeConverter
class UserLocationConverter {
    @TypeConverter
    fun toDatabaseType(locationKey: LocationKey): String? {
        return Gson().toJson(locationKey)
    }

    @TypeConverter
    fun toAppType(data: String): LocationKey {
        return Gson().fromJson(data, LocationKey::class.java)
    }
}