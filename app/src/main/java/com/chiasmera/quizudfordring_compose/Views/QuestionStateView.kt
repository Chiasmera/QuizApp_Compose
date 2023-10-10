package com.chiasmera.quizudfordring_compose

import android.text.Html
import android.text.Html.FROM_HTML_MODE_LEGACY
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chiasmera.quizudfordring_compose.Controller.QuestionState
import com.chiasmera.quizudfordring_compose.Data.SampleData
import com.chiasmera.quizudfordring_compose.Model.Question
import com.chiasmera.quizudfordring_compose.Views.AnswerList
import com.chiasmera.quizudfordring_compose.Views.ErrorScreen
import com.chiasmera.quizudfordring_compose.Views.LoadingScreen

@Composable
fun QuestionStateView(
    currentQuestionIndex : Int,
    questionState: QuestionState,
    onNavigateToCategories : () -> Unit = {},
    onNextQuestion : () -> Unit = {}
) {
    val index by remember { mutableIntStateOf(currentQuestionIndex) }

    when (questionState) {
        is QuestionState.Success -> CurrentQuestionView(
            currentQuestion = questionState.questions[index],
            onNextQuestion = onNextQuestion,
            onNavigateToCategories = onNavigateToCategories,
            isLast = index < questionState.questions.count() - 1
        )
        is QuestionState.Loading -> LoadingScreen()
        QuestionState.Error -> ErrorScreen()
    }
}

@Composable
fun CurrentQuestionView(
    currentQuestion : Question,
    onNavigateToCategories : () -> Unit = {},
    onNextQuestion : () -> Unit = {},
    isLast: Boolean
) {

    Box (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column (
            modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = Html.fromHtml(currentQuestion.question, FROM_HTML_MODE_LEGACY).toString(),
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier

            )

            AnswerList(currentQuestion)
        }

        Row (modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.BottomCenter),
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
                    modifier = Modifier
                ) {
                    Text("Next Question")
                }
            }

        }


    }
}
@Preview(showSystemUi = true)
@Composable
fun QuestionPreview () {
    CurrentQuestionView(
        currentQuestion = SampleData.questions[0],
        onNextQuestion = {},
        onNavigateToCategories = {  },
        isLast = 0 < SampleData.questions.count() - 1
    )
}