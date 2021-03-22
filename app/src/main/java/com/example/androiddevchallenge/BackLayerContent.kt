
package com.example.androiddevchallenge

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieAnimationSpec
import com.airbnb.lottie.compose.rememberLottieAnimationState
import com.example.androiddevchallenge.data.DailyModel
import com.example.androiddevchallenge.data.animationByTime

@ExperimentalFoundationApi
@Composable
fun BackLayerContent(item : DailyModel) {
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
                Spacer(modifier = Modifier.height(40.dp))
                Text(
                    text = "Los Angeles",
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.h2
                )
                Text(
                    text = "Chance of Rain: 3%",
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.h2
                )
                Text(
                    text = item.temperature,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.h1
                )
                Spacer(modifier = Modifier.height(32.dp))
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
                    val animationSpec = LottieAnimationSpec.RawRes(animationByTime(item.time))
                    val animationState =
                        rememberLottieAnimationState(
                            autoPlay = true,
                            repeatCount = Integer.MAX_VALUE
                        )

                    LottieAnimation(
                        spec = animationSpec,
                        modifier = Modifier
                            .height(100.dp)
                            .width(100.dp),
                        animationState = animationState
                    )
                }

            }
        }
    }
}
