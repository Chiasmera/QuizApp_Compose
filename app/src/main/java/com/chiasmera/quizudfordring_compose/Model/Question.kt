package com.chiasmera.quizudfordring_compose.Model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Question(
    val category: String,
    val type: String,
    val difficulty: String,
    val question: String,
    @SerialName(value = "correct_answer")
    val correctAnswer: String,
    @SerialName(value = "incorrect_answers")
    val incorrectAnswers: List<String>
) {
    var allAnswers: MutableList<String> = mutableListOf()
    init {
        allAnswers.addAll(incorrectAnswers)
        allAnswers.add(correctAnswer)
        allAnswers.shuffle()
    }
}