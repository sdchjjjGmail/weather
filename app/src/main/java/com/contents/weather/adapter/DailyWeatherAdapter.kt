package com.contents.weather.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.contents.domain.model.DailyForecast
import com.contents.weather.databinding.ViewDailyWeatherItemBinding
import com.contents.weather.utils.ExpandAnimation
import com.contents.weather.utils.TimeConverter

class DailyWeatherAdapter : ListAdapter<DailyForecast, DailyWeatherAdapter.DailyWeatherViewHolder?>(
    PlaceCallback
) {

    class DailyWeatherViewHolder(private val binding: ViewDailyWeatherItemBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DailyForecast) {
            binding.weather = item
            binding.date.text = TimeConverter.getDate(context, item.epochDate!!)
            binding.expandableItem.visibility = View.GONE
            binding.itemContainer.setOnClickListener {
                with(binding.expandableItem) {
                    if (visibility == View.GONE) {
                        ExpandAnimation.expand(binding.expandableItem)
                    } else {
                        ExpandAnimation.collapse(binding.expandableItem)
                    }
                }
            }
        }
    }

    companion object {
        private val PlaceCallback = object : DiffUtil.ItemCallback<DailyForecast>() {
            override fun areItemsTheSame(oldItem: DailyForecast, newItem: DailyForecast): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

            override fun areContentsTheSame(
                oldItem: DailyForecast,
                newItem: DailyForecast
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DailyWeatherViewHolder {
        val binding =
            ViewDailyWeatherItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DailyWeatherViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: DailyWeatherViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
