package com.example.androiddevchallenge

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.BackdropValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.DailyModel
import com.example.androiddevchallenge.data.listByDay
import com.example.androiddevchallenge.data.weeklyData
import com.example.androiddevchallenge.ui.theme.BottomSheetShape

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun BottomsheetSection(
    modifier: Modifier = Modifier,
    backDropValue: BackdropValue,
    onHeaderClicked: () -> Unit,
) {
    Surface(modifier = modifier.fillMaxSize(), color = Color.White, shape = BottomSheetShape) {
        val listState = rememberLazyListState()
        var daySelected by remember { mutableStateOf(weeklyData.first()) }
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize()
        ) {
            stickyHeader {
                Column(
                    modifier = Modifier
                        .background(MaterialTheme.colors.onPrimary)
                        .clickable(onClick = { onHeaderClicked() })
                        .fillMaxSize()
                        .padding(top = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        modifier = Modifier
                            .rotate(backDropValue.degreesFromState())
                            .width(24.dp)
                            .height(12.dp),
                        painter = painterResource(id = R.drawable.ic_up), contentDescription = null
                    )
                }
                LazyRow(
                    modifier = Modifier.background(MaterialTheme.colors.onPrimary),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    items(weeklyData) { itemContent ->
                        DailyItem(daySelected,itemContent, onDaySelected = { daySelected = it })
                    }
                }
            }


            items(items = listByDay(daySelected)) {
                TimeItem(
                    modifier = Modifier.fillMaxWidth(),
                    item = it
                )
                Divider()
            }
        }
    }
}

@Composable
private fun TimeItem(
    modifier: Modifier = Modifier,
    item: DailyModel
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.padding(horizontal = 24.dp, vertical = 12.dp)
    ) {

        WeatherImage(
            modifier = Modifier.size(24.dp),
            imageRes = item.icon,
            description = item.title,
            scale = ContentScale.Crop
        )

        Spacer(Modifier.width(8.dp))
        Text(
            text = item.title,
            style = MaterialTheme.typography.caption
        )
        Spacer(Modifier.width(8.dp))
        WeatherImage(
            modifier = Modifier.size(24.dp),
            imageRes = R.drawable.ic_warm,
            scale = ContentScale.Crop,
        )
        Text(
            text = item.temperature,
            style = MaterialTheme.typography.caption
        )
        WeatherImage(
            modifier = Modifier.size(20.dp),
            imageRes = R.drawable.ic_humidity,
            scale = ContentScale.Crop,
        )
        Text(
            text = item.umidity.toString(),
            style = MaterialTheme.typography.caption
        )

    }
}

@Composable
fun Divider(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
) {
    Canvas(
        modifier = modifier.fillMaxWidth(),
        onDraw = {
            drawLine(
                color = color,
                start = Offset(x = 0f, y = 0f),
                end = Offset(x = size.width, y = size.height),
                pathEffect = PathEffect.dashPathEffect(
                    intervals = floatArrayOf(10f, 20f),
                    phase = 25f
                )
            )
        }
    )
}

@ExperimentalMaterialApi
fun BackdropValue.degreesFromState() =
    when (this) {
        BackdropValue.Concealed -> 180f
        BackdropValue.Revealed -> 0f
    }