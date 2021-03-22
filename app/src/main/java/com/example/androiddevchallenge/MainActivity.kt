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

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.material.BackdropScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.rememberBackdropScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.dayWeatherByTime
import com.example.androiddevchallenge.ui.theme.MyTheme
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel>()

    @ExperimentalFoundationApi
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp(viewModel = viewModel)
            }
        }
    }
}

@Composable
private fun HomeTabBar(
    tabSelected: TimeOfDay,
    onTabSelected: (TimeOfDay) -> Unit,
    modifier: Modifier = Modifier
) {
    WeatherTabBar(
        modifier = modifier
    ) { tabBarModifier ->
        WeatherTabs(
            modifier = tabBarModifier,
            tabs = TimeOfDay.values().toList(),
            tabSelected = tabSelected,
            onTabSelected = { newTab -> onTabSelected(TimeOfDay.values()[newTab.ordinal]) }
        )
    }
}

// Start building your app here!
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun MyApp(viewModel: MainViewModel) {

    var tabSelected by remember { mutableStateOf(TimeOfDay.Morning) }
    val scaffoldState = rememberBackdropScaffoldState(initialValue = viewModel.scaffolldState())
    val coroutineScope = rememberCoroutineScope()
    BackdropScaffold(
        modifier = Modifier.background(MaterialTheme.colors.background),
        headerHeight = 195.dp,
        scaffoldState = scaffoldState,
        frontLayerScrimColor = Color.Transparent,
        appBar = {
            HomeTabBar(tabSelected, onTabSelected = { tabSelected = it })
        },
        backLayerContent = {
            dayWeatherByTime(tabSelected)?.let {
                BackLayerContent(it)
            }
        },
        frontLayerContent = {
            BottomsheetSection(
                backDropValue = scaffoldState.currentValue,
                onHeaderClicked = {
                    coroutineScope.launch {
                        if (scaffoldState.isConcealed) {
                            scaffoldState.reveal()
                        } else {
                            scaffoldState.conceal()
                        }
                    }
                    viewModel.onScaffoldStateChanged()
                }
            )
        }
    )
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp(MainViewModel())
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp(MainViewModel())
    }
}
