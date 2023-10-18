package com.contents.weather.utils

import com.contents.weather.R

object WeatherIconProvider {
    fun getIcon(iconNumber: Int): Int {
        return when (iconNumber) {
            1 -> R.drawable.weather_01
            2 -> R.drawable.weather_02
            3 -> R.drawable.weather_03
            4 -> R.drawable.weather_04
            5 -> R.drawable.weather_05
            6 -> R.drawable.weather_06
            7 -> R.drawable.weather_07
            8 -> R.drawable.weather_08
            11 -> R.drawable.weather_11
            12 -> R.drawable.weather_12
            13 -> R.drawable.weather_13
            14 -> R.drawable.weather_14
            15 -> R.drawable.weather_15
            16 -> R.drawable.weather_16
            17 -> R.drawable.weather_17
            18 -> R.drawable.weather_18
            19 -> R.drawable.weather_19
            20 -> R.drawable.weather_20
            21 -> R.drawable.weather_21
            22 -> R.drawable.weather_22
            23 -> R.drawable.weather_23
            24 -> R.drawable.weather_24
            25 -> R.drawable.weather_25
            26 -> R.drawable.weather_26
            29 -> R.drawable.weather_29
            30 -> R.drawable.weather_30
            31 -> R.drawable.weather_31
            32 -> R.drawable.weather_32
            33 -> R.drawable.weather_33
            34 -> R.drawable.weather_34
            35 -> R.drawable.weather_35
            36 -> R.drawable.weather_36
            37 -> R.drawable.weather_37
            38 -> R.drawable.weather_38
            39 -> R.drawable.weather_39
            40 -> R.drawable.weather_40
            41 -> R.drawable.weather_41
            42 -> R.drawable.weather_42
            43 -> R.drawable.weather_43
            44 -> R.drawable.weather_44
            else -> R.drawable.weather_01
        }
    }
}