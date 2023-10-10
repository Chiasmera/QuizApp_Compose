package com.chiasmera.quizudfordring_compose.Views.QuestionScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material3.ElevatedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun QuestionBottomButtonBar(
    modifier : Modifier = Modifier,
    onNavigateToCategories : () -> Unit = {},
    isLast: Boolean,
    onNextQuestion: () -> Unit = {},
    correctAnswerFound:Boolean
) {
    Row (modifier = Modifier
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ElevatedButton(
            onClick = onNavigateToCategories,
            modifier = Modifier
        ) {
            Text("Categories")
        }

        if (isLast) {
            ElevatedButton(
                onClick = onNextQuestion,
                modifier = Modifier,
                enabled = correctAnswerFound
            ) {
                Text("Next Question")
            }
        }

    }
}

@Preview
@Composable
fun QuestionButtonBarPreview_unanswered() {
    QuestionBottomButtonBar(
        isLast = false,
        correctAnswerFound = false
    )
}

@Preview
@Composable
fun QuestionButtonBarPreview_answered() {
    QuestionBottomButtonBar(
        isLast = false,
        correctAnswerFound = true
    )
}

@Preview
@Composable
fun QuestionButtonBarPreview_lastQuestion() {
    QuestionBottomButtonBar(
        isLast = true,
        correctAnswerFound = true
    )
}