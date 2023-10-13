package com.chiasmera.quizudfordring_compose.controller

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chiasmera.quizudfordring_compose.model.Category
import com.chiasmera.quizudfordring_compose.model.Question
import com.chiasmera.quizudfordring_compose.service.QuizAPI
import kotlinx.coroutines.Job
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface CategoryState {
    data class Success(val categories: List<Category>) : CategoryState
    object Error : CategoryState
    object Loading : CategoryState
}

sealed interface QuestionState {
    data class Success(val questions: List<Question>) : QuestionState
    object Error : QuestionState
    object Loading : QuestionState
}

/**
 * Viewmodel for the app. Keeps the state for the list of categories, and the current list of questions.
 * Also facilitates communication between layers.
 */
class QuizViewModel : ViewModel() {
    var categoryState: CategoryState by mutableStateOf(CategoryState.Loading)
    var questionState: QuestionState by mutableStateOf(QuestionState.Loading)

    init {
        fetchCategories()
    }

    /**
     * Prompts the service layer to fetch all availible categories from the api, and fill each with the amount of questions for that category
     */
    fun fetchCategories() {
        viewModelScope.launch {
            categoryState = try {
                val categoryListResult = QuizAPI.retrofitService.getCategoryList().triviaCategories

                val fetchCountJobs = mutableListOf<Job>()
                for (category in categoryListResult) {
                    fetchCountJobs.add(launch { fetchQuestionCountForCategory(category) })
                }

                fetchCountJobs.joinAll()

                CategoryState.Success(categoryListResult.sortedWith(compareBy { it.name }))
            } catch (e: IOException) {
                CategoryState.Error
            }
        }
    }

    /**
     * Helper method, that fetches and populates the number of questions for each difficulty in a given category
     */
    private suspend fun fetchQuestionCountForCategory(category: Category) {

        try {
            val categoryQuestionCount =
                QuizAPI.retrofitService.getNumberOfQuestionsForCategory(category.id).categoryQuestionCount
            category.easyCount = categoryQuestionCount.totalEasyQuestionCount
            category.mediumCount = categoryQuestionCount.totalMediumQuestionCount
            category.hardCount = categoryQuestionCount.totalHardQuestionCount
            category.totalCount = categoryQuestionCount.totalQuestionCount
        } catch (e: IOException) {
            Log.e("QUIZVIEWMODEL", "Failed to fetch question count for category: ${category.name}")
            CategoryState.Error
        }


    }

    /**
     * fetches a given amount of questions from the specified category and difficulty into the current list of questions
     */
    fun fetchQuestions(
        amount: Int, categoryID: Int, difficulty: String
    ) {
        viewModelScope.launch {
            questionState = try {
                val questionResponse =
                    QuizAPI.retrofitService.getQuestionsForCategory(amount, categoryID, difficulty)
                QuestionState.Success(questionResponse.results)
            } catch (e: IOException) {
                Log.e(
                    "QUIZVIEWMODEL",
                    "Failed to fetch questions for category id: ${categoryID} and difficulty ${difficulty}"
                )
                QuestionState.Error
            }
        }
    }
}