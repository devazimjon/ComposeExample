package com.example.myapplication.ui.compose.lazylayout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import ua.hospes.lazygrid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.compose.lazylayout.item.Banner
import com.example.myapplication.ui.compose.lazylayout.item.FastCategories
import com.example.myapplication.ui.compose.lazylayout.item.Product
import com.example.myapplication.ui.compose.lazylayout.item.TabContent
import com.example.myapplication.ui.compose.lazylayout.item.VerticalProductContent
import ua.hospes.lazygrid.GridCells
import ua.hospes.lazygrid.GridItemSpan
import ua.hospes.lazygrid.items
import ua.hospes.lazygrid.rememberLazyGridState
import kotlin.random.Random

@Composable
fun MainPage() {

    Column {
        SearchBar()

        val state = rememberLazyGridState()
        LazyVerticalGrid(
            state = state,
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {

            item(
                key = "banner",
                span = { GridItemSpan(2) }
            ) {
                Banner()
            }
            item(
                key = "fastCategory",
                span = { GridItemSpan(2) }
            ) {
                FastCategories()
            }

            stickyHeader(
                key = "stickHeader",
                span = { GridItemSpan(2) }
            ){
                TabContent()
            }

            items(
                items = products(),
                key = { it.id },
                span = { GridItemSpan(1) }
            ) {
                VerticalProductContent(item = it)
            }
        }
    }
}

private fun products(): List<Product> = buildList {
    repeat(10) {
        Product(
            id = Random.nextInt(),
            name = "Name$it",
            detail = "Detail$it"
        ).let(::add)
    }
}


@Composable
private fun SearchBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(Color.Red)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "Search bar"
        )
    }
}

@Composable
@Preview
fun MainPagePreview() {
    MainPage()
}