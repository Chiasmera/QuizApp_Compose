package com.chiasmera.quizudfordring_compose.service

import com.chiasmera.quizudfordring_compose.model.Category
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A response from tha API containing all categories
 */
@Serializable
data class CategoryResult(
    @SerialName(value = "trivia_categories") val triviaCategories: List<Category>
)