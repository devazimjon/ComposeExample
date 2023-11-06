package com.example.myapplication.ui.compose

import android.graphics.Paint.Style
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.with
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.launch

@Composable
fun MainContent() {
    val TopWithFooter = object : Arrangement.Vertical {
        override fun Density.arrange(
            totalSize: Int, sizes: IntArray, outPositions: IntArray
        ) {
            var y = 0
            sizes.forEachIndexed { index, size ->
                outPositions[index] = y
                y += size
            }
            if (y < totalSize) {
                val lastIndex = outPositions.lastIndex
                outPositions[lastIndex] = totalSize - sizes.last()
            }
        }
    }
    MyApplicationTheme {
        Box() {
            LineGraphContent(3,4)
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 100.dp),
                verticalArrangement = TopWithFooter
            ) {
                item {
                    HeaderContent()
                }

                itemsIndexed(items = arrayOf("A", "B", "C"),
                    contentType = { _, _ -> 2 }) { index, item ->
                    ItemContent(item, index)
                }
                item(contentType = 1) {
                    HorizontalOffer(5)
                }

            }
        }

    }
}

@Composable
fun LineGraphContent(row: Int, column: Int) {

    Box(modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)
        .padding(2.dp)
        .drawBehind {
            val heightOffsetDiff = size.height / row
            val widthOffsetDiff = size.width / column
            repeat(row + 1) {
                val start = Offset(0f, it * heightOffsetDiff)
                val end = Offset(size.width, it * heightOffsetDiff)
                drawLine(Color.Gray, start, end)
            }
            repeat(column + 1) {
                val start = Offset(it * widthOffsetDiff, 0f)
                val end = Offset(it * widthOffsetDiff, size.height)
                drawLine(Color.Gray, start, end)
            }
        }
    ) {
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SwitchableContent(input: Int) {
    AnimatedContent(
        targetState = input,
        label = "label",
    ) {
        when (it) {
            1 -> HorizontalOffer(size = 5)
            2 -> GridItemsContent(size = 5)
            else -> HeaderContent()
        }
    }
}

@Composable
fun ProgressContent(
    index: Int,
    totalCount: Int
) {
    val progress by animateFloatAsState(
        targetValue = (index + 1) / totalCount.toFloat(), label = ""
    )

    LinearProgressIndicator(
        progress = progress
    )
}

@Composable
fun AvatarContent() {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val rotationAnimation = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(tween(1000, easing = LinearEasing)),
        label = ""
    )
    Image(
        modifier = Modifier
            .drawBehind {
                rotate(rotationAnimation.value) {
                    drawCircle(color = Color.Black, style = Stroke(2f))
                }
            }
            .size(40.dp)
            .clip(CircleShape),
        painter = painterResource(id = R.drawable.ic_launcher_foreground),
        contentDescription = ""
    )
}

@Composable
fun HorizontalOffer(size: Int) {
    val data = List(size) { "VerticalList$it" }

    val state = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    LazyRow(
        modifier = Modifier.fillMaxWidth(), state = state, contentPadding = PaddingValues(
            start = 16.dp, end = 16.dp
        ), horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(data) {
            Column {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = ""
                )
                Text(text = it)
            }
        }
    }

    val showScrollButton by remember {
        derivedStateOf {
            state.firstVisibleItemIndex > 0
        }
    }
    if (showScrollButton) {
        Button(onClick = {
            coroutineScope.launch {
                state.animateScrollToItem(0)
            }
        }) {
            Text(text = "scroll top")
        }
    }
}

@Composable
fun GridItemsContent(size: Int) {
    val data = List(size) { "VerticalList$it" }
    val state = rememberLazyGridState()

    LazyHorizontalGrid(
        state = state,
        rows = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(data) {
            Column {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = ""
                )
                Text(text = it)
            }
        }
    }
}

@Composable
fun HeaderContent() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "It's row")
        Text(text = "Title")
    }
}

@Composable
fun ItemContent(text: String, index: Int) {
    var showButton = remember { false }
    var maxLines = remember { 8 }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp), horizontalAlignment = Alignment.Start
    ) {
        Text(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
            maxLines = 8,
            overflow = TextOverflow.Ellipsis,
            text = "Title$index",
            onTextLayout = {
                if (it.hasVisualOverflow) {
                    showButton = true
                }
            })
        if (showButton) {
            Button(onClick = {
                maxLines = Int.MAX_VALUE
            }) {
                Text(text = "show more")
            }
        }
        Text(text = "Message: $text")
    }
}

@Preview()
@Composable
fun MainContentPreview() {
    MainContent()
}