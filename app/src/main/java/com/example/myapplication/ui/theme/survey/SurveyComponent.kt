package com.example.myapplication.ui.theme.survey

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SurveyComponent(
    question: String,
    answers: List<Answer>
) {
    Surface(
        color = MaterialTheme.colorScheme.primary
    ) {
        Scaffold(
            topBar = { SurveyTopBar() },
            bottomBar = { SurveyBottomBar() },
            content = { innerPadding ->
                Box(
                    modifier = Modifier.padding(innerPadding)
                ) {
                    AnswersContent(question, answers)
                }
            }
        )
    }
}

@Composable
private fun AnswersContent(
    question: String,
    answers: List<Answer>
) {
    var selectedAnswer: Answer? by rememberSaveable { mutableStateOf(null) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Text(text = question, style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.padding(top = 24.dp))
        Column(
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            answers.forEach { answer ->
                AnswerContent(
                    answer = answer,
                    isSelected = selectedAnswer == answer,
                    onClick = { selectedAnswer = it }
                )
            }
        }
    }
}

@Composable
private fun AnswerContent(
    answer: Answer,
    isSelected: Boolean,
    onClick: (Answer) -> Unit
) {
    Surface(
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick(answer) }
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null
            )
            Text(text = answer.title)
            RadioButton(selected = isSelected, onClick = { onClick(answer) })
        }
    }
}

@Composable
private fun SurveyTopBar() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        color = MaterialTheme.colorScheme.inversePrimary,
        border = BorderStroke(1.dp, Color.Black)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "TopBar"
        )
    }
}

@Composable
private fun SurveyBottomBar() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        color = MaterialTheme.colorScheme.inversePrimary,
        border = BorderStroke(1.dp, Color.Black)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "BottomBar"
        )
    }
}