package com.contents.weather.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.contents.weather.R
import com.contents.weather.databinding.FragmentCurrentWeatherBinding
import com.contents.weather.utils.EventBus
import com.contents.weather.viewmodel.CurrentWeatherViewModel
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraAnimation
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CurrentWeatherFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentCurrentWeatherBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CurrentWeatherViewModel by viewModels()

    private var naverMap: NaverMap? = null
    private var marker = Marker()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCurrentWeatherBinding.inflate(layoutInflater).apply {
            viewModel = this@CurrentWeatherFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
        }
        val fm = childFragmentManager
        val mapFragment = fm.findFragmentById(binding.naverMapContainer.id) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(binding.naverMapContainer.id, it).commit()
            }
        mapFragment.getMapAsync(this@CurrentWeatherFragment)
        marker.setOnClickListener {
            showCheckWeatherOnMapPopup(marker.position)
            true
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.naverMapPosition.observe(viewLifecycleOwner) {
            setMapMarker(LatLng(it.latitude, it.longitude).also { position ->
                moveToMapMarker(position)
            })
        }
    }

    override fun onMapReady(naverMap: NaverMap) {
        this.naverMap = naverMap
        this.naverMap!!.setOnMapClickListener { _, coordinator ->
            setMapMarker(LatLng(coordinator.latitude, coordinator.longitude))
        }
    }

    private fun setMapMarker(position: LatLng) {
        marker.apply {
            this.position = position
            this.map = naverMap
        }
    }

    private fun moveToMapMarker(position: LatLng) {
        naverMap!!.moveCamera(
            CameraUpdate.scrollTo(position)
                .animate(CameraAnimation.Easing, 1000)
        )
    }

    private fun showCheckWeatherOnMapPopup(position: LatLng) {
        AlertDialog.Builder(context)
            .setTitle(getString(R.string.check_weather_this_place))
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                CoroutineScope(Dispatchers.Default).launch {
                    EventBus.post(position)
                }
            }
            .setNegativeButton(getString(R.string.no)) { _, _ -> }
            .create()
            .show()
    }
}