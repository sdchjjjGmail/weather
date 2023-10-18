package com.contents.weather.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.contents.weather.adapter.WeatherViewPagerAdapter
import com.contents.weather.databinding.FragmentHomeWeatherBinding
import com.contents.weather.utils.getViewPagerTitle
import com.contents.weather.viewmodel.HomeWeatherViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeWeatherFragment : Fragment() {

    private val args: HomeWeatherFragmentArgs by navArgs()

    private var _binding: FragmentHomeWeatherBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeWeatherViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeWeatherBinding.inflate(layoutInflater).apply {
            fragment = this@HomeWeatherFragment
            viewModel = this@HomeWeatherFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
        }
        initViewPager()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.addLocation(args.userLocation)
        TabLayoutMediator(binding.tabLayout, binding.viewPagerWeather) { tab, position ->
            tab.text = getViewPagerTitle(requireContext(), position)
        }.attach()
    }

    private fun initViewPager() {
        val pagerAdapter = WeatherViewPagerAdapter(
            fragmentActivity = requireActivity(),
        )
        binding.viewPagerWeather.adapter = pagerAdapter
        binding.viewPagerWeather.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.viewPagerWeather.offscreenPageLimit = 2
        binding.viewPagerWeather.currentItem = 0
        binding.viewPagerWeather.isUserInputEnabled = false

        binding.viewPagerWeather.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }
        })
    }
}