package com.example.androiddevchallenge

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BackdropScaffoldState
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.DailyModel
import com.example.androiddevchallenge.data.ExploreModel
import com.example.androiddevchallenge.data.emptyDailyModel
import com.example.androiddevchallenge.data.listByDay
import com.example.androiddevchallenge.data.weeklyData
import com.example.androiddevchallenge.ui.theme.BottomSheetShape
import dev.chrisbanes.accompanist.coil.CoilImage

typealias OnExploreItemClicked = (ExploreModel) -> Unit

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun ExploreSection(
    modifier: Modifier = Modifier,
    backDropValue: BackdropValue
) {
    Surface(modifier = modifier.fillMaxSize(), color = Color.White, shape = BottomSheetShape) {
        val listState = rememberLazyListState()
        var daySelected by remember { mutableStateOf(emptyDailyModel) }
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize()
        ) {
            stickyHeader {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        modifier = Modifier
                            .rotate(backDropValue.degreesFromState())
                            .size(18.dp),
                        painter = painterResource(id = R.drawable.ic_up), contentDescription = null
                    )
                }
                Spacer(Modifier.height(8.dp))
                LazyRow(contentPadding = PaddingValues(horizontal = 0.dp, vertical = 0.dp)) {
                    items(weeklyData) { itemContent ->
                        DailyItem(itemContent, onDaySelected = { daySelected = it })
                    }
                }
            }


            items(items = listByDay(daySelected)) {
                ExploreItem(
                    modifier = Modifier.fillMaxWidth(),
                    item = it
                )
            }
        }
    }
}

@Composable
private fun ExploreItem(
    modifier: Modifier = Modifier,
    item: DailyModel
) {
    Row(
        modifier = modifier
            .padding(top = 12.dp, bottom = 12.dp)
    ) {
        ExploreImageContainer {
            WeatherImage(
                modifier = Modifier
                    .clip(CircleShape)
                    .height(88.dp)
                    .width(88.dp),
                imageRes = item.icon,
                description = item.title,
                scale = ContentScale.Crop
            )

        }
        Spacer(Modifier.width(24.dp))

        Text(
            text = item.title,
            style = MaterialTheme.typography.h6
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = item.temperature,
            style = MaterialTheme.typography.caption
        )
        Text(
            text = item.umidity.toString(),
            style = MaterialTheme.typography.caption
        )

    }
}

@Composable
private fun ExploreImageContainer(children: @Composable () -> Unit) {
    Surface(Modifier.size(width = 60.dp, height = 60.dp), RoundedCornerShape(4.dp)) {
        children()
    }
}

@ExperimentalMaterialApi
fun BackdropValue.degreesFromState() =
    when (this) {
        BackdropValue.Concealed -> 180f
        BackdropValue.Revealed -> 0f
    }