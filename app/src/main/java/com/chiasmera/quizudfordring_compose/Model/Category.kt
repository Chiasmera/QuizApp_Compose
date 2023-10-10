package com.chiasmera.quizudfordring_compose.Model

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val id: Int,
    val name: String
)