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
package com.example.androiddevchallenge

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.DailyModel
import com.example.androiddevchallenge.ui.theme.primaryColor
import com.example.androiddevchallenge.ui.theme.secondaryColor

@Composable
fun DailyItem(
    daySelected: DailyModel,
    itemContent: DailyModel,
    onDaySelected: (DailyModel) -> Unit
) {

    var border = BorderStroke(3.dp, Color.White)
    if (daySelected == itemContent) {
        border = BorderStroke(3.dp, primaryColor)
    }
    Card(
        modifier = Modifier.padding(start = 4.dp),
        shape = RoundedCornerShape(16.dp),
        border = border,
        backgroundColor = secondaryColor
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 4.dp)
                .fillMaxWidth()
                .clickable(onClick = { onDaySelected(itemContent) }),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            WeatherImage(
                modifier = Modifier
                    .padding(8.dp)
                    .size(60.dp),
                imageRes = itemContent.icon,
                description = itemContent.title,
                scale = ContentScale.Crop,
            )

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = itemContent.title,
                    modifier = Modifier.padding(top = 8.dp),
                    style = MaterialTheme.typography.h3
                )
                Text(
                    text = itemContent.temperature,
                    modifier = Modifier.padding(top = 8.dp),
                    style = MaterialTheme.typography.h2
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = itemContent.umidity.toString(),
                        modifier = Modifier.padding(top = 8.dp),
                        style = MaterialTheme.typography.h2
                    )
                    WeatherImage(
                        modifier = Modifier.size(18.dp),
                        imageRes = R.drawable.ic_humidity,
                        scale = ContentScale.Crop,
                    )
                }
            }
        }
    }
}
