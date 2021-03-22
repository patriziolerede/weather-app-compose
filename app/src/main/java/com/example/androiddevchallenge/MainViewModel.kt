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

import androidx.compose.material.BackdropValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _scaffoldExpanded = MutableLiveData(false)

    fun onScaffoldStateChanged() {
        _scaffoldExpanded.value = _scaffoldExpanded.value?.not()
    }

    @ExperimentalMaterialApi
    fun scaffolldState() =
        if (_scaffoldExpanded.value == true) BackdropValue.Concealed else BackdropValue.Revealed
}
