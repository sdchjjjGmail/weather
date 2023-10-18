package com.contents.weather

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.naver.maps.map.NaverMapSdk
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        NaverMapSdk.getInstance(this).client =
            NaverMapSdk.NaverCloudPlatformClient(BuildConfig.X_NCP_APIGW_API_KEY_ID)
        AndroidThreeTen.init(this)
    }
}