package com.contents.weather.utils

import android.content.Context
import com.contents.weather.R
import org.threeten.bp.DayOfWeek
import org.threeten.bp.Instant
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter

object TimeConverter {
    fun getTime(context: Context, epoch: Long): String {
        return with(LocalDateTime.ofInstant(
            Instant.ofEpochMilli(epoch * 1000),
            ZoneId.systemDefault()
        )) {
            "${checkToday(context, toLocalDate())}${ format(DateTimeFormatter.ofPattern("a hh:mm")) }"
        }
    }

    private fun checkToday(context: Context, date: LocalDate): String {
        return when (date) {
            LocalDate.now().plusDays(1) -> context.getString(R.string.tomorrow)
            LocalDate.now().plusDays(2) -> context.getString(R.string.day_after_tomrw)
            else -> ""
        }
    }

    fun getDate(context: Context, epoch: Long): String {
        return with(LocalDateTime.ofInstant(
            Instant.ofEpochMilli(epoch * 1000),
            ZoneId.systemDefault()
        )) {
            "${ format(DateTimeFormatter.ofPattern("MM/dd")) }(${translateDay(context, dayOfWeek)})"
        }
    }

    private fun translateDay(context: Context, day: DayOfWeek): String {
        return when(day) {
            DayOfWeek.MONDAY -> context.getString(R.string.mon)
            DayOfWeek.TUESDAY -> context.getString(R.string.tue)
            DayOfWeek.WEDNESDAY -> context.getString(R.string.wed)
            DayOfWeek.THURSDAY -> context.getString(R.string.thu)
            DayOfWeek.FRIDAY -> context.getString(R.string.fri)
            DayOfWeek.SATURDAY -> context.getString(R.string.sat)
            DayOfWeek.SUNDAY -> context.getString(R.string.sun)
        }
    }
}