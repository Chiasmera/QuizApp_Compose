package com.chiasmera.quizudfordring_compose.Service

import com.chiasmera.quizudfordring_compose.Model.Category
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryResult (
    @SerialName(value="trivia_categories")
    val triviaCategories : List<Category>
)