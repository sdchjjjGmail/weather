package com.contents.weather.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.contents.weather.adapter.DailyWeatherAdapter
import com.contents.weather.databinding.FragmentDailyWeatherBinding
import com.contents.weather.viewmodel.DailyWeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DailyWeatherFragment : Fragment() {

    private var _binding: FragmentDailyWeatherBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DailyWeatherViewModel by viewModels()

    private val dailyWeatherAdapter by lazy {
        DailyWeatherAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDailyWeatherBinding.inflate(layoutInflater).apply {
            viewModel = this@DailyWeatherFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
        }
        binding.dailyWeatherRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = dailyWeatherAdapter
            itemAnimator = null
        }
        return binding.root
    }
}