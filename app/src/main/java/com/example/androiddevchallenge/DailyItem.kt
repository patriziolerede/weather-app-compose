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

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.DailyModel

@Composable
fun DailyItem(itemContent: DailyModel,  onDaySelected: (DailyModel) -> Unit) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .fillMaxWidth()
            .clickable(onClick = { onDaySelected(itemContent) }),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        WeatherImage(
            modifier = Modifier
                .clip(CircleShape)
                .height(88.dp)
                .width(88.dp),
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
            Text(
                text = itemContent.umidity.toString(),
                modifier = Modifier.padding(top = 24.dp),
                style = MaterialTheme.typography.h2
            )
        }
    }
}
