package com.contents.weather.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.contents.weather.adapter.HourlyWeatherAdapter
import com.contents.weather.databinding.FragmentHourlyWeatherBinding
import com.contents.weather.viewmodel.HourlyWeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HourlyWeatherFragment : Fragment() {

    private var _binding: FragmentHourlyWeatherBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HourlyWeatherViewModel by viewModels()

    private val hourlyWeatherAdapter by lazy {
        HourlyWeatherAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHourlyWeatherBinding.inflate(layoutInflater).apply {
            viewModel = this@HourlyWeatherFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
        }
        binding.hourlyWeatherRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = hourlyWeatherAdapter
        }
        return binding.root
    }
}