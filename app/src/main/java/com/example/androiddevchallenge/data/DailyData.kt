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
import kotlin.random.Random

data class DailyModel(
    val title: String,
    @DrawableRes val icon: Int,
    val temperature: String,
    val umidity: Int,
    val time: TimeOfDay
)

val weeklyData = listOf(
    DailyModel(
        title = "Today",
        temperature = "22°",
        icon = R.drawable.ic_sun,
        umidity = 95,
        time = TimeOfDay.Morning
    ),
    DailyModel(
        title = "Tomorrow 24",
        temperature = "18°",
        icon = R.drawable.ic_cloud,
        umidity = 70,
        time = TimeOfDay.Evening
    ),
    DailyModel(
        title = "Thursday 25",
        temperature = "18°",
        icon = R.drawable.ic_cloudy,
        umidity = 70,
        time = TimeOfDay.Morning
    ),
    DailyModel(
        title = "Friday 26",
        temperature = "16°",
        icon = R.drawable.ic_rainy,
        umidity = 70,
        time = TimeOfDay.Morning
    ),
    DailyModel(
        title = "Saturday 27",
        temperature = "15°",
        icon = R.drawable.ic_sun,
        umidity = 67,
        time = TimeOfDay.Morning
    ),
    DailyModel(
        title = "Sunday 28",
        temperature = "17°",
        icon = R.drawable.ic_rainy,
        umidity = 54,
        time = TimeOfDay.Evening
    ),
    DailyModel(
        title = "Monday 29",
        temperature = "10°",
        icon = R.drawable.ic_snowy,
        umidity = 89,
        time = TimeOfDay.Evening
    )
)

val dayData = listOf(
    DailyModel(
        title = "Today",
        temperature = "17°",
        icon = R.drawable.ic_storm,
        umidity = 95,
        time = TimeOfDay.Morning
    ),
    DailyModel(
        title = "Today",
        temperature = "19°",
        icon = R.drawable.ic_snowy,
        umidity = 70,
        time = TimeOfDay.Day
    ),
    DailyModel(
        title = "Today",
        temperature = "15°",
        icon = R.drawable.ic_storm,
        umidity = 70,
        time = TimeOfDay.Evening
    ),
    DailyModel(
        title = "Today",
        temperature = "14°",
        icon = R.drawable.ic_cloud,
        umidity = 70,
        time = TimeOfDay.Night
    )
)

fun listByDay(day: DailyModel) = (0..23).map {
    DailyModel(
        title = it.toString().padStart(2, '0') + ":00",
        temperature = "${Random.nextInt(18, 25)}°",
        icon = when (Random.nextInt(0, 4)) {
            0 -> R.drawable.ic_cloud
            1 -> R.drawable.ic_storm
            2 -> R.drawable.ic_cloudy
            3 -> R.drawable.ic_sun
            else -> R.drawable.ic_rainy
        },
        umidity = Random.nextInt(50, 98),
        time = TimeOfDay.values().random()
    )
}

fun animationByTime(time: TimeOfDay): Int {
    return when (time) {
        TimeOfDay.Morning, TimeOfDay.Day -> dayAnimations.random()
        TimeOfDay.Evening, TimeOfDay.Night -> nightAnimations.random()
    }
}

fun dayWeatherByTime(time: TimeOfDay): DailyModel? {
    return dayData.firstOrNull { it.time == time }
}

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
