package com.example.myapplication.ui.compose.lazylayout.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R

data class Product(
    val id: Int,
    val name: String,
    val detail: String
)

@Composable
fun VerticalProductContent(item: Product) {
    Column(
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
    ) {
        Image(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "null"
        )
        Text(
            modifier = Modifier
                .padding(top = 12.dp)
                .align(Alignment.CenterHorizontally),
            text = item.name
        )
        Text(
            modifier = Modifier
                .padding(top = 12.dp)
                .align(Alignment.CenterHorizontally),
            text = item.detail
        )
    }
}