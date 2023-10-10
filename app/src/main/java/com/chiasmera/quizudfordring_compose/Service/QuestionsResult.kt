package com.chiasmera.quizudfordring_compose.Service

import com.chiasmera.quizudfordring_compose.Model.Question
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuestionsResult (
    @SerialName(value="response_code")
    val responseCode : Int,
    val results : List<Question>
)