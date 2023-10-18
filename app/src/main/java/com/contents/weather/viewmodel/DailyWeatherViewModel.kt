package com.contents.weather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.contents.domain.interactor.GetDailyWeatherUseCase
import com.contents.domain.model.DailyForecast
import com.contents.domain.model.LocationKey
import com.contents.domain.model.UserLocation
import com.contents.weather.BuildConfig
import com.contents.weather.utils.EventBus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DailyWeatherViewModel @Inject constructor(
    private val getDailyWeatherUseCase: GetDailyWeatherUseCase,
) : ViewModel() {

    init {
        onPlaceSelected()
    }

    private val _dailyWeather = MutableLiveData<List<DailyForecast?>>()
    val dailyWeather get() = _dailyWeather

    private fun getDailyWeather(locationKey: LocationKey) {
        viewModelScope.launch(Dispatchers.Default) {
            getDailyWeatherUseCase(
                locationKey.key,
                BuildConfig.ACCU_API_KEY,
                "ko-kr",
                true
            )
                .onStart { }
                .catch { it.printStackTrace() }
                .collect {
                    _dailyWeather.postValue(it.dailyForecast!!)
                }
        }
    }

    private fun onPlaceSelected() {
        viewModelScope.launch(Dispatchers.Default) {
            EventBus.subscribe<UserLocation>()
                .collect {
                    getDailyWeather(it.locationKey)
                }
        }
    }
}