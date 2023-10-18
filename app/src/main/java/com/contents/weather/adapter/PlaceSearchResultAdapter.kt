package com.contents.weather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.contents.domain.model.Place
import com.contents.weather.databinding.ViewPlaceSearchResultItemBinding

class PlaceSearchResultAdapter(private val clickListener: PlaceClickListener) :
    ListAdapter<Place, PlaceSearchResultAdapter.PlaceSearchResultViewHolder?>(
        PlaceCallback
    ) {

    class PlaceSearchResultViewHolder(private val binding: ViewPlaceSearchResultItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Place, clickListener: PlaceClickListener) {
            binding.click = clickListener
            binding.place = item
            with(binding) {
                if (item.roadAddress.isNotEmpty()) {
                    address.text = item.roadAddress
                } else {
                    address.text = item.address
                }
            }
        }
    }

    companion object {
        private val PlaceCallback = object : DiffUtil.ItemCallback<Place>() {
            override fun areItemsTheSame(oldItem: Place, newItem: Place): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

            override fun areContentsTheSame(oldItem: Place, newItem: Place): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlaceSearchResultViewHolder {
        val binding = ViewPlaceSearchResultItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PlaceSearchResultViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaceSearchResultViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }
}


class PlaceClickListener(val clickListener: (place: Place) -> Unit) {
    fun onClick(place: Place) = clickListener(place)
}