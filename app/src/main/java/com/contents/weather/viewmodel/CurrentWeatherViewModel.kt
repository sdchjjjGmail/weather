package com.contents.weather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.contents.domain.interactor.GetCurrentWeatherUseCase
import com.contents.domain.model.CurrentWeather
import com.contents.domain.model.UserLocation
import com.contents.weather.BuildConfig
import com.contents.weather.model.NaverMapPosition
import com.contents.weather.utils.EventBus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase
) : ViewModel() {

    init {
        onPlaceSelected()
    }

    private val _naverMapPosition = MutableLiveData<NaverMapPosition>()
    val naverMapPosition get() = _naverMapPosition
    private val _temperature = MutableLiveData("")
    val temperature get() = _temperature
    private val _weatherIcon = MutableLiveData(0)
    val weatherIcon get() = _weatherIcon

    private fun getCurrentWeather(userLocation: UserLocation?) {
        if (userLocation != null) {
            viewModelScope.launch(Dispatchers.Default) {
                getCurrentWeatherUseCase(
                    userLocation.locationKey.key,
                    BuildConfig.ACCU_API_KEY,
                    "ko-kr"
                )
                    .onStart { }
                    .catch { it.printStackTrace() }
                    .collect {
                        setCurrentWeather(it)
                        _naverMapPosition.postValue(
                            NaverMapPosition(
                                userLocation.latitude,
                                userLocation.longitude
                            )
                        )
                    }
            }
        } else {
            setCurrentWeather(null)
        }
    }

    private fun setCurrentWeather(weatherData: CurrentWeather?) {
        if (weatherData != null) {
            _temperature.postValue("${weatherData.temperature.metric.value}℃")
            _weatherIcon.postValue(weatherData.weatherIcon)
        } else {
            _temperature.postValue("")
            _weatherIcon.postValue(0)
        }
    }

    private fun onPlaceSelected() {
        viewModelScope.launch(Dispatchers.Default) {
            EventBus.subscribe<UserLocation>()
                .collect {
                    getCurrentWeather(it)
                }
        }
    }
}