package com.contents.weather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.contents.domain.interactor.GetHourlyWeatherUseCase
import com.contents.domain.model.HourlyWeatherItem
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
class HourlyWeatherViewModel @Inject constructor(
    private val getHourlyWeatherUseCase: GetHourlyWeatherUseCase,
) : ViewModel() {

    init {
        onPlaceSelected()
    }

    private val _hourlyWeather = MutableLiveData<List<HourlyWeatherItem>>()
    val hourlyWeather get() = _hourlyWeather

    private fun getHourlyWeather(locationKey: LocationKey) {
        viewModelScope.launch(Dispatchers.Default) {
            getHourlyWeatherUseCase(
                locationKey.key,
                BuildConfig.ACCU_API_KEY,
                "ko-kr",
                true
            )
                .onStart { }
                .catch { it.printStackTrace() }
                .collect {
                    _hourlyWeather.postValue(it)
                }
        }
    }

    private fun onPlaceSelected() {
        viewModelScope.launch(Dispatchers.Default) {
            EventBus.subscribe<UserLocation>()
                .collect {
                    getHourlyWeather(it.locationKey)
                }
        }
    }
}