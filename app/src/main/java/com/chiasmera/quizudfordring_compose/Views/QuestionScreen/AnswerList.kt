package com.chiasmera.quizudfordring_compose.Views.QuestionScreen

import android.text.Html
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.chiasmera.quizudfordring_compose.Model.Question

@Composable
fun AnswerList(
    currentQuestion: Question,
    onCorrectAnswer: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier

            .fillMaxHeight(0.7F),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        for (answer in currentQuestion.allAnswers) {

            var currentColor by remember { mutableStateOf(Color.Transparent) }

            FilledTonalButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(currentColor),
                onClick = {
                    if (answer == currentQuestion.correctAnswer) {
                        currentColor = Color.Green
                        onCorrectAnswer(true)
                    } else {
                        currentColor = Color.Red
                        onCorrectAnswer(false)
                    }
                }
            ) {
                Text(
                    text = Html.fromHtml(answer, Html.FROM_HTML_MODE_LEGACY).toString(),
                    style = MaterialTheme.typography.headlineMedium
                )
            }

        }
    }
}