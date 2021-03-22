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

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush.Companion.radialGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieAnimationSpec
import com.airbnb.lottie.compose.rememberLottieAnimationState
import com.example.androiddevchallenge.data.DailyModel
import com.example.androiddevchallenge.data.animationByTime
import com.example.androiddevchallenge.ui.theme.primaryColor
import kotlin.random.Random

@ExperimentalFoundationApi
@Composable
fun BackLayerContent(item: DailyModel) {
    Surface(color = MaterialTheme.colors.background) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    text = stringResource(id = R.string.city),
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.h1
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = stringResource(id = R.string.chance_rain) + "${Random.nextInt(3, 25)}%",
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.h2
                )

                Spacer(modifier = Modifier.height(30.dp))
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
                    val animationSpec = LottieAnimationSpec.RawRes(animationByTime(item.time))
                    val animationState =
                        rememberLottieAnimationState(
                            autoPlay = true,
                            repeatCount = Integer.MAX_VALUE
                        )

                    Text(
                        text = item.temperature,
                        style = MaterialTheme.typography.h1.copy(fontSize = 120.sp)
                    )
                    LottieAnimation(
                        spec = animationSpec,
                        modifier = Modifier
                            .padding(top = 50.dp)
                            .size(230.dp)
                            .background(
                                radialGradient(
                                    listOf(primaryColor.copy(alpha = 0.8f), Color.Transparent)
                                )
                            ),
                        animationState = animationState
                    )
                }
            }
        }
    }
}
