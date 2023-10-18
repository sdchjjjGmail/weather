package com.contents.weather.viewmodel

import android.app.AlertDialog
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.contents.domain.interactor.DeleteUserLocationUseCase
import com.contents.domain.interactor.GetLocationKeyUseCase
import com.contents.domain.interactor.GetUserLocationsUseCase
import com.contents.domain.interactor.SaveUserLocationUseCase
import com.contents.domain.model.UserLocation
import com.contents.weather.BuildConfig
import com.contents.weather.R
import com.contents.weather.fragment.HomeWeatherFragment
import com.contents.weather.fragment.HomeWeatherFragmentDirections
import com.contents.weather.utils.EventBus
import com.naver.maps.geometry.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class HomeWeatherViewModel @Inject constructor(
    private val getUserLocationsUseCase: GetUserLocationsUseCase,
    private val deleteUserLocationUseCase: DeleteUserLocationUseCase,
    private val saveUserLocationUseCase: SaveUserLocationUseCase,
    private val getLocationKeyUseCase: GetLocationKeyUseCase,
) : ViewModel() {

    init {
        getUserLocations()
        checkWeatherOnMap()
    }

    private val _selectedContainerId = MutableLiveData(0L)
    val selectedContainerId get() = _selectedContainerId
    private val _firstContainerLocation = MutableLiveData("")
    val firstContainerLocation get() = _firstContainerLocation
    private val _secondContainerLocation = MutableLiveData("")
    val secondContainerLocation get() = _secondContainerLocation
    private val _thirdContainerLocation = MutableLiveData("")
    val thirdContainerLocation get() = _thirdContainerLocation

    private val userLocationMap: HashMap<Long, UserLocation>? = HashMap()
    private var currentSelectedLocation = 0L

    private fun getUserLocations() {
        viewModelScope.launch(Dispatchers.Default) {
            getUserLocationsUseCase()
                .onStart { }
                .catch { it.printStackTrace() }
                .collect {
                    setUserLocationMap(it)
                }
        }
    }

    private fun setUserLocationMap(list: List<UserLocation>) {
        if (userLocationMap != null) {
            userLocationMap.clear()
            if (list.isNotEmpty()) {
                runBlocking {
                    list.forEach {
                        userLocationMap[it.id] = it
                    }
                }
                if (currentSelectedLocation == 0L) {
                    currentSelectedLocation = userLocationMap.keys.first()
                }
                setPlace()
            }
        }
        setContainers()
    }

    fun addLocation(userLocation: UserLocation?) {
        if (userLocation != null) {
            currentSelectedLocation = userLocation.id
            saveUserLocation(userLocation)
        }
    }

    private fun saveUserLocation(userLocation: UserLocation) {
        viewModelScope.launch(Dispatchers.Default) {
            if (saveUserLocationUseCase(userLocation) <= 0) {
                // TODO: Failed to save place
            }
        }
    }

    private fun setContainers() {
        with(userLocationMap) {
            if (this@with != null) {
                _firstContainerLocation.postValue(getName(userLocationMap!![1]))
                _secondContainerLocation.postValue(getName(userLocationMap[2]))
                _thirdContainerLocation.postValue(getName(userLocationMap[3]))
            } else {
                _firstContainerLocation.postValue("+")
                _secondContainerLocation.postValue("+")
                _thirdContainerLocation.postValue("+")
            }
        }
    }

    private fun getName(userLocation: UserLocation?): String {
        return userLocation?.locationKey?.localizedName ?: "+"
    }

    fun onLocationClicked(fragment: HomeWeatherFragment, containerId: Long) {
        if (userLocationMap!![containerId] == null) {
            moveToPlaceSearchFragment(fragment, containerId)
        } else {
            currentSelectedLocation = containerId
            setPlace()
        }
    }

    private fun setPlace() {
        _selectedContainerId.postValue(currentSelectedLocation)
        viewModelScope.launch(Dispatchers.Default) {
            if (currentSelectedLocation == 0L) {
                EventBus.post(null)
            } else {
                EventBus.post(userLocationMap!![currentSelectedLocation])
            }
        }
    }

    fun onLocationLongClicked(fragment: HomeWeatherFragment, containerId: Long): Boolean {
        with(userLocationMap!![containerId]) {
            if (this@with != null) {
                showLocationOptions(fragment, this@with)
            }
        }
        return true
    }

    private fun showLocationOptions(fragment: HomeWeatherFragment, userLocation: UserLocation) {
        with(fragment) {
            AlertDialog.Builder(context)
                .setTitle(userLocation.locationKey.localizedName)
                .setMessage(getString(R.string.this_place))
                .setPositiveButton(getString(R.string.replace)) { _, _ ->
                    moveToPlaceSearchFragment(
                        this@with,
                        userLocation.id
                    )
                }
                .setNegativeButton(getString(R.string.delete)) { _, _ ->
                    if (currentSelectedLocation == userLocation.id) {
                        initSelectedContainer()
                    }
                    deletePlace(userLocation)
                }
                .create()
                .show()
        }
    }

    private fun deletePlace(userLocation: UserLocation) {
        viewModelScope.launch(Dispatchers.Default) {
            deleteUserLocationUseCase(userLocation)
        }
    }

    private fun initSelectedContainer() {
        currentSelectedLocation = 0
        _selectedContainerId.postValue(0)
    }

    private fun moveToPlaceSearchFragment(fragment: HomeWeatherFragment, containerId: Long) {
        fragment.findNavController().navigate(
            HomeWeatherFragmentDirections.actionHomeWeatherFragmentToPlaceSearchFragment(
                containerId
            )
        )
    }

    private fun getLocationKey(position: LatLng) {
        viewModelScope.launch(Dispatchers.Default) {
            getLocationKeyUseCase(
                BuildConfig.ACCU_API_KEY,
                "${position.latitude},${position.longitude}",
                "ko-kr"
            )
                .collect {
                    if (it != null) {
                        EventBus.post(
                            UserLocation(
                                id = 0,
                                locationKey = it,
                                latitude = position.latitude,
                                longitude = position.longitude
                            )
                        )
                    }
                }
        }
    }

    private fun checkWeatherOnMap() {
        viewModelScope.launch(Dispatchers.Default) {
            EventBus.subscribe<LatLng>()
                .collect {
                    getLocationKey(it)
                }
        }
    }
}