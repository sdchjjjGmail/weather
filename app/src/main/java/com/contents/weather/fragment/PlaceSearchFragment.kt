package com.contents.weather.fragment

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.contents.domain.model.Place
import com.contents.domain.model.UserLocation
import com.contents.weather.R
import com.contents.weather.adapter.PlaceClickListener
import com.contents.weather.adapter.PlaceSearchResultAdapter
import com.contents.weather.databinding.FragmentPlaceSearchBinding
import com.contents.weather.viewmodel.PlaceSearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlaceSearchFragment : Fragment() {

    private val args: PlaceSearchFragmentArgs by navArgs()

    private var _binding: FragmentPlaceSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PlaceSearchViewModel by viewModels()

    private val placeSearchResultAdapter by lazy {
        PlaceSearchResultAdapter(PlaceClickListener { place -> requestPlaceRegister(place) })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                moveToWeather(null)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlaceSearchBinding.inflate(layoutInflater).apply {
            viewModel = this@PlaceSearchFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
        }
        binding.placeResultRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = placeSearchResultAdapter
        }
        binding.placeSearchInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.search()
                false
            } else {
                true
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setContainerId(args.containerId)
        viewModel.searchResult.observe(viewLifecycleOwner) {
            hideKeyboard()
            placeSearchResultAdapter.submitList(it)
        }
        viewModel.userLocation.observe(viewLifecycleOwner) {
            moveToWeather(it)
        }
    }

    private fun requestPlaceRegister(place: Place) {
        val builder = AlertDialog.Builder(requireActivity())
        builder
            .setTitle(place.title)
            .setMessage(getString(R.string.ask_register_place))
            .setPositiveButton(getString(R.string.yes)) { _, _ -> viewModel.onPlaceSelected(place) }
            .setNegativeButton(getString(R.string.no)) { _, _ -> }
            .create()
            .show()
    }

    private fun moveToWeather(userLocation: UserLocation?) {
        this@PlaceSearchFragment.findNavController().navigate(
            PlaceSearchFragmentDirections.actionPlaceSearchFragmentToHomeWeatherFragment(
                userLocation
            )
        )
    }

    private fun hideKeyboard() {
        if (requireActivity().currentFocus != null) {
            val inputManager =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(
                requireActivity().currentFocus!!.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }
}