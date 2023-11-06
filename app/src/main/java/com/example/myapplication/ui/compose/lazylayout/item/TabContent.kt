package com.example.myapplication.ui.compose.lazylayout.item

import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun TabContent() {
    val titles = listOf("Tab 1", "Tab 2", "Tab 3")
    var tabIndex by remember { mutableStateOf(0) }
    TabRow(selectedTabIndex = tabIndex) {
        titles.forEachIndexed { index, title ->
            Tab(
                text = { Text(title) },
                selected = tabIndex == index,
                onClick = { tabIndex = index }
            )
        }
    }
}