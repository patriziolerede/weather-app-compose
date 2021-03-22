/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.data

import androidx.annotation.DrawableRes
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.TimeOfDay

data class DailyModel(
    val title: String,
    @DrawableRes val icon: Int,
    val temperature: String,
    val umidity: Int,
    val time: TimeOfDay
)

val emptyDailyModel = DailyModel("",R.drawable.ic_11_sunny,"",0,TimeOfDay.Morning)

val weeklyData = listOf(
    DailyModel(
        title = "Today",
        temperature = "5°/7°",
        icon = R.drawable.ic_12_windy,
        umidity = 95,
        time = TimeOfDay.Morning
    ),
    DailyModel(
        title = "Tomorrow",
        temperature = "6°/8°",
        icon = R.drawable.ic_14_hail,
        umidity = 70,
        time = TimeOfDay.Evening
    ),
    DailyModel(
        title = "Monday",
        temperature = "6°/8°",
        icon = R.drawable.ic_11_sunny,
        umidity = 70,
        time = TimeOfDay.Morning
    ),
    DailyModel(
        title = "Tomorrow",
        temperature = "6°/8°",
        icon = R.drawable.ic_15_sunrise,
        umidity = 70,
        time = TimeOfDay.Morning
    )
)

val dayData = listOf(
    DailyModel(
        title = "Today",
        temperature = "5°/7°",
        icon = R.drawable.ic_12_windy,
        umidity = 95,
        time = TimeOfDay.Morning
    ),
    DailyModel(
        title = "Today",
        temperature = "6°/8°",
        icon = R.drawable.ic_14_hail,
        umidity = 70,
        time = TimeOfDay.Afternoon
    ),
    DailyModel(
        title = "Today",
        temperature = "6°/8°",
        icon = R.drawable.ic_11_sunny,
        umidity = 70,
        time = TimeOfDay.Evening
    ),
    DailyModel(
        title = "Today",
        temperature = "6°/8°",
        icon = R.drawable.ic_15_sunrise,
        umidity = 70,
        time = TimeOfDay.Overnight
    )
)

fun animationByTime(time: TimeOfDay): Int {
    return when (time) {
        TimeOfDay.Morning, TimeOfDay.Afternoon -> dayAnimations.random()
        TimeOfDay.Evening, TimeOfDay.Overnight -> nightAnimations.random()
    }
}

fun dayWeatherByTime(time: TimeOfDay): DailyModel? {
    return dayData.firstOrNull { it.time == time }
}

fun listByDay(day: DailyModel) = weeklyData.filter { it.title == day.title }

val dayAnimations = listOf(
    R.raw.weather_day_broken_clouds,
    R.raw.weather_day_clear_sky,
    R.raw.weather_day_few_clouds,
    R.raw.weather_day_mist,
    R.raw.weather_day_rain,
    R.raw.weather_day_scattered_clouds,
    R.raw.weather_day_thunderstorm,
    R.raw.weather_day_shower_rains,
    R.raw.weather_day_snow
)

val nightAnimations = listOf(
    R.raw.weather_night_broken_clouds,
    R.raw.weather_night_clear_sky,
    R.raw.weather_night_mist,
    R.raw.weather_night_rain,
    R.raw.weather_night_scattered_clouds,
    R.raw.weather_night_shower_rains,
    R.raw.weather_night_snow,
    R.raw.weather_night_thunderstorm
)


