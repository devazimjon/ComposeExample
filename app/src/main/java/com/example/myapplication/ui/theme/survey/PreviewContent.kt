package com.example.myapplication.ui.theme.survey

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.R
import com.example.myapplication.ui.theme.MyApplicationTheme

@Composable
fun AnswerContent() {
    Row {
        Image(painter = painterResource(R.drawable.ic_launcher_background), contentDescription = "")
        Text(text = "Jet Survey")
        RadioButton(selected = false, onClick = { /*TODO*/ })
    }
}

@Preview(fontScale = 1.5f,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    showBackground = true
)
@Preview(showBackground = true, backgroundColor = 0xFF411414,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    device = "spec:width=1080px,height=2340px,dpi=440,isRound=true"
)
@Composable
fun AnswerContentPrev() {
    MyApplicationTheme {
        AnswerContent()
    }
}
