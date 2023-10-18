package com.contents.weather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.contents.domain.interactor.GetCoordinatesUseCase
import com.contents.domain.interactor.GetLocationKeyUseCase
import com.contents.domain.interactor.SearchPlaceUseCase
import com.contents.domain.model.GeocodeModel
import com.contents.domain.model.Place
import com.contents.domain.model.UserLocation
import com.contents.weather.BuildConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PlaceSearchViewModel @Inject constructor(
    private val searchPlaceUseCase: SearchPlaceUseCase,
    private val getCoordinatesUseCase: GetCoordinatesUseCase,
    private val getLocationKeyUseCase: GetLocationKeyUseCase,
) : ViewModel() {

    private val _searchResult = MutableLiveData<List<Place>>()
    val searchResult get() = _searchResult
    private val _resultSuccess = MutableLiveData(false)
    val resultSuccess get() = _resultSuccess
    private val _resultErr = MutableLiveData(false)
    val resultErr get() = _resultErr
    private val _userLocation = MutableLiveData<UserLocation>()
    val userLocation get() = _userLocation

    private var currentPlace = ""
    private var selectedLocationContainerId = 0L

    fun setContainerId(id: Long) {
        selectedLocationContainerId = id
    }

    fun setInputPlace(input: CharSequence) {
        currentPlace = input.toString()
    }

    fun search() {
        if (currentPlace.isBlank()) return
        viewModelScope.launch(Dispatchers.Default) {
            searchPlaceUseCase(
                BuildConfig.X_NAVER_CLIENT_ID,
                BuildConfig.X_NAVER_CLIENT_SECRET,
                currentPlace
            )
                .onStart {
                    _resultErr.postValue(false)
                    _resultSuccess.postValue(false)
                }
                .catch { _resultErr.postValue(true) }
                .collect { response ->
                    if (response.items.isNotEmpty()) {
                        setResult(response.items)
                    } else {
                        _resultErr.postValue(true)
                    }
                }
        }
    }

    private fun setResult(data: List<Place>) {
        _searchResult.postValue(data)
        _resultSuccess.postValue(true)
    }

    fun onPlaceSelected(place: Place) {
        if (place.roadAddress.isNotBlank()) {
            getCoordinates(place.roadAddress)
        } else {
            getCoordinates(place.address)
        }
    }

    private fun getCoordinates(address: String) {
        viewModelScope.launch(Dispatchers.Default) {
            getCoordinatesUseCase(
                BuildConfig.X_NCP_APIGW_API_KEY_ID,
                BuildConfig.X_NCP_APIGW_API_KEY,
                address
            )
                .onStart { }
                .catch { it.printStackTrace() }
                .collect {
                    if (it.x == "" || it.y == "") {
                        // search fail message
                    } else {
                        getLocationKey(it)
                    }
                }
        }
    }

    private suspend fun getLocationKey(geocodeModel: GeocodeModel) {
        getLocationKeyUseCase(
            BuildConfig.ACCU_API_KEY,
            "${geocodeModel.y},${geocodeModel.x}",
            "ko-kr"
        )
            .onStart { }
            .catch { it.printStackTrace() }
            .collect {
                if (it != null) {
                    _userLocation.postValue(
                        UserLocation(
                            selectedLocationContainerId,
                            it,
                            latitude = geocodeModel.y.toDouble(),
                            longitude = geocodeModel.x.toDouble(),
                        )
                    )
                }
            }
    }
}