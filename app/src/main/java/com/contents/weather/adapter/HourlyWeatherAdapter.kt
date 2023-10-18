package com.contents.weather.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.contents.domain.model.HourlyWeatherItem
import com.contents.weather.databinding.ViewHourlyWeatherItemBinding
import com.contents.weather.utils.TimeConverter

class HourlyWeatherAdapter :
    ListAdapter<HourlyWeatherItem, HourlyWeatherAdapter.HourlyWeatherViewHolder?>(
        PlaceCallback
    ) {

    class HourlyWeatherViewHolder(private val binding: ViewHourlyWeatherItemBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HourlyWeatherItem) {
            binding.weather = item
            binding.time.text = TimeConverter.getTime(context, item.epochDateTime!!)
        }
    }

    companion object {
        private val PlaceCallback = object : DiffUtil.ItemCallback<HourlyWeatherItem>() {
            override fun areItemsTheSame(
                oldItem: HourlyWeatherItem,
                newItem: HourlyWeatherItem
            ): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

            override fun areContentsTheSame(
                oldItem: HourlyWeatherItem,
                newItem: HourlyWeatherItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HourlyWeatherViewHolder {
        val binding =
            ViewHourlyWeatherItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HourlyWeatherViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: HourlyWeatherViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
