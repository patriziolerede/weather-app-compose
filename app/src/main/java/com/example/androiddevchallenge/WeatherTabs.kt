/*
 * Copyright 2020 The Android Open Source Project
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

package com.example.androiddevchallenge

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import java.sql.Time

@Composable
fun WeatherTabBar(
    modifier: Modifier = Modifier,
    children: @Composable (Modifier) -> Unit
) {
    Row(modifier) {
        children(
            Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        )
    }
}

enum class TimeOfDay(val resourceId: Int) {
    Morning(R.drawable.ic_dawn),
    Day(R.drawable.ic_day),
    Evening(R.drawable.ic_sunset),
    Night(R.drawable.ic_night)
}

@Composable
fun WeatherTabs(
    modifier: Modifier = Modifier,
    tabs: List<TimeOfDay>,
    tabSelected: TimeOfDay,
    onTabSelected: (TimeOfDay) -> Unit
) {
    TabRow(
        selectedTabIndex = tabSelected.ordinal,
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.onSurface,
        indicator = { },
        divider = { }
    ) {
        tabs.forEachIndexed { index, tab ->
            val selected = index == tabSelected.ordinal

            var tabModifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
            if (selected) {
                tabModifier =
                    Modifier
                        .border(BorderStroke(2.dp, Color.White), RoundedCornerShape(16.dp))
                        .then(tabModifier)
            }

            Tab(
                selected = selected,
                onClick = { onTabSelected(TimeOfDay.values()[index]) }
            ) {
                Column(
                    modifier = tabModifier,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        style = MaterialTheme.typography.body1.copy(color = Color.White),

                        text = tab.name
                    )
                    Spacer(Modifier.height(8.dp))
                    WeatherImage(
                        scale = ContentScale.Crop,
                        imageRes = tab.resourceId,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}
