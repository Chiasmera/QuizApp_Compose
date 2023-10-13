package com.chiasmera.quizudfordring_compose.views.questionScreen

import android.text.Html
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chiasmera.quizudfordring_compose.data.SampleData
import com.chiasmera.quizudfordring_compose.model.Question

@Composable
fun CurrentQuestionView(
    modifier: Modifier = Modifier, currentQuestion: Question, onCorrectAnswer: (Boolean) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = Html.fromHtml(currentQuestion.question, Html.FROM_HTML_MODE_LEGACY).toString(),
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
        )

        AnswerList(
            currentQuestion = currentQuestion, onCorrectAnswer = onCorrectAnswer
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun QuestionPreview() {
    CurrentQuestionView(modifier = Modifier.padding(16.dp),
        currentQuestion = SampleData.questions[0],
        onCorrectAnswer = { })
}