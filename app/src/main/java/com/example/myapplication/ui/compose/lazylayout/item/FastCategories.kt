package com.example.myapplication.ui.compose.lazylayout.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FastCategories() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Color.Yellow)
    ) {
        LazyRow() {
            items(fastCategoryItems) {
                FastCategory(it)
            }
        }
    }
}

@Composable
fun FastCategory(title: String) {
    Column(
        modifier = Modifier
            .padding(start = 12.dp)
            .height(36.dp)
    ) {
        Box(
            modifier = Modifier
                .width(20.dp)
                .height(20.dp)
        )
        Text(text = title)
    }
}

private val fastCategoryItems = listOf(
    "Tezkor",
    "Nasiya",
    "Uzum Bank",
    "Uzum Auto"
)