package com.chiasmera.quizudfordring_compose.service

import com.chiasmera.quizudfordring_compose.model.Question
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A response from the API, containing a list of questions and a responsecode
 */
@Serializable
data class QuestionsResult(
    @SerialName(value = "response_code") val responseCode: Int, val results: List<Question>
)