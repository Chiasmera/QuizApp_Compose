package com.chiasmera.quizudfordring_compose.model

import kotlinx.serialization.Serializable

/**
 * Represents a category of questions.
 * Has no knowledge of the questions that belong in the category, but knows the count for each difficulty
 */
@Serializable
data class Category(
    val id: Int,
    val name: String,
    var easyCount: Int = 0,
    var mediumCount: Int = 0,
    var hardCount: Int = 0,
    var totalCount: Int = 0
)