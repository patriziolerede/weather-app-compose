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
import androidx.compose.material.BackdropScaffold
import androidx.compose.material.BackdropValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBackdropScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.dayWeatherByTime
import com.example.androiddevchallenge.ui.theme.MyTheme

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
    openDrawer: () -> Unit,
    tabSelected: TimeOfDay,
    onTabSelected: (TimeOfDay) -> Unit,
    modifier: Modifier = Modifier
) {
    CraneTabBar(
        modifier = modifier,
        onMenuClicked = openDrawer
    ) { tabBarModifier ->
        WeatherTabs(
            modifier = tabBarModifier,
            titles = TimeOfDay.values().map { it.name },
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
    val scaffoldState = rememberBackdropScaffoldState(initialValue = BackdropValue.Revealed)
    BackdropScaffold(
        modifier = Modifier,
        headerHeight = 100.dp,
        scaffoldState = scaffoldState,
        frontLayerScrimColor = Color.Transparent,
        appBar = {
            HomeTabBar({ }, tabSelected, onTabSelected = { tabSelected = it })
        },
        backLayerContent = {
            dayWeatherByTime(tabSelected)?.let {
                BackLayerContent(it)
            }
        },
        frontLayerContent = {
            ExploreSection(
                backDropValue = scaffoldState.currentValue
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
