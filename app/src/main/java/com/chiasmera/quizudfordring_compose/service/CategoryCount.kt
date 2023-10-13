package com.chiasmera.quizudfordring_compose.service

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Models a response from the api, containing the number of questions for all difficulties in a given category
 */
@Serializable
class CategoryCount(
    @SerialName(value = "category_id") val categoryID: Int,
    @SerialName(value = "category_question_count") val categoryQuestionCount: CategoryQuestionCount
)

/**
 * Contains the number of questions for its parent CategoryCount
 */
@Serializable
class CategoryQuestionCount(
    @SerialName(value = "total_question_count") val totalQuestionCount: Int,
    @SerialName(value = "total_easy_question_count") val totalEasyQuestionCount: Int,
    @SerialName(value = "total_medium_question_count") val totalMediumQuestionCount: Int,
    @SerialName(value = "total_hard_question_count") val totalHardQuestionCount: Int
)