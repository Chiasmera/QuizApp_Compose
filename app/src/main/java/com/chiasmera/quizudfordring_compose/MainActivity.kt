package com.chiasmera.quizudfordring_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chiasmera.quizudfordring_compose.Controller.Destinations
import com.chiasmera.quizudfordring_compose.Controller.QuizViewModel
import com.chiasmera.quizudfordring_compose.Views.CategoryScreen.CategoryStateView
import com.chiasmera.quizudfordring_compose.ui.theme.QuizUdfordring_ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            QuizUdfordringApp()
        }
    }
}

@Composable
fun QuizUdfordringApp(
    quizViewModel: QuizViewModel = viewModel()
) {
    QuizUdfordring_ComposeTheme() {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = Destinations.CategoryViewDestination.route
        ) {
            composable(route = Destinations.CategoryViewDestination.route) {
                CategoryStateView(
                    categoryState = quizViewModel.categoryState,
                    onCategoryChosen = { amount: Int, catID: Int, difficulty: String ->
                        quizViewModel.fetchQuestions(amount, catID, difficulty)
                        navController.navigateToQuestion(0) }

                )
            }

            composable(
                route = Destinations.QuestionViewDestination.routeWithArgs,
                arguments = Destinations.QuestionViewDestination.arguments
            ) {
                val index  = it.arguments?.getInt(Destinations.QuestionViewDestination.argName)
                if (index != null) {
                    QuestionStateView(
                        currentQuestionIndex = index,
                        questionState = quizViewModel.questionState,
                        onNavigateToCategories = { navController.navigate(Destinations.CategoryViewDestination.route) },
                        onNextQuestion = { navController.navigateToQuestion(index+1) }
                    )
                }

            }
        }
    }
}

private fun NavHostController.navigateToQuestion( index: Int) =
    this.navigate("${Destinations.QuestionViewDestination.route}/${index}")