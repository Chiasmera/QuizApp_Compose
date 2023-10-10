package com.chiasmera.quizudfordring_compose.Controller

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chiasmera.quizudfordring_compose.Model.Category
import com.chiasmera.quizudfordring_compose.Model.Question
import com.chiasmera.quizudfordring_compose.Service.QuizAPI
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

class QuizViewModel : ViewModel() {
    var categoryState : CategoryState by mutableStateOf(CategoryState.Loading)
    var questionState : QuestionState by mutableStateOf(QuestionState.Loading)

    init {
        fetchCategories()
    }

    fun fetchCategories () {
        viewModelScope.launch {
            categoryState = try {
                val categoryListResult = QuizAPI.retrofitService.getCategoryList()
                CategoryState.Success(categoryListResult.triviaCategories.sortedWith(compareBy { it.name }))
            } catch (e: IOException) {
                CategoryState.Error
            }
        }
    }

    fun fetchQuestions (
        amount:Int,
        categoryID :Int,
        difficulty: String
    )  {
        viewModelScope.launch {
            questionState = try {
                val questionResponse = QuizAPI.retrofitService.getQuestionsForCategory(amount, categoryID, difficulty)
                QuestionState.Success(questionResponse.results)
            } catch (e: IOException) {
                QuestionState.Error
            }
        }
    }
}