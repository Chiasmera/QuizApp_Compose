package com.chiasmera.quizudfordring_compose

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.chiasmera.quizudfordring_compose.Controller.QuestionState
import com.chiasmera.quizudfordring_compose.Views.QuestionScreen.CurrentQuestionView
import com.chiasmera.quizudfordring_compose.Views.Shared.ErrorScreen
import com.chiasmera.quizudfordring_compose.Views.Shared.LoadingScreen
import com.chiasmera.quizudfordring_compose.Views.QuestionScreen.QuestionBottomButtonBar

@Composable
fun QuestionStateView(
    modifier: Modifier = Modifier,
    currentQuestionIndex: Int,
    questionState: QuestionState,
    onNavigateToCategories: () -> Unit = {},
    onNextQuestion: () -> Unit = {}
) {
    val index by remember { mutableIntStateOf(currentQuestionIndex) }
    var correctAnswerFound by remember { mutableStateOf(false) }


    when (questionState) {
        is QuestionState.Loading -> LoadingScreen()

        is QuestionState.Success -> Scaffold(
            topBar = { TopAppBar(title = { Text("question ${index + 1} out of ${questionState.questions.count()}") }) },
            bottomBar = {
                QuestionBottomButtonBar(
                    onNavigateToCategories = onNavigateToCategories,
                    onNextQuestion = onNextQuestion,
                    isLast = index < questionState.questions.count() - 1,
                    correctAnswerFound = correctAnswerFound
                )
            },
            modifier = Modifier

        ) {
            CurrentQuestionView(
                modifier = Modifier.padding(it),
                currentQuestion = questionState.questions[index],
                onCorrectAnswer = { correct -> correctAnswerFound = correct }
            )
        }


        QuestionState.Error -> ErrorScreen()
    }
}