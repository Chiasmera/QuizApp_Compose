package com.chiasmera.quizudfordring_compose.Service

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_CATEGORY_URL = "https://opentdb.com/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_CATEGORY_URL)
    .build()

interface QuizNetworkService {

    @GET("api_category.php")
    suspend fun getCategoryList() : CategoryResult

    @GET("api.php")
    suspend fun getQuestionsForCategory(
        @Query("amount") amount : Int,
        @Query("category") categoryID : Int,
        @Query("difficulty") difficulty : String ) : QuestionsResult
}

object QuizAPI {
    val retrofitService : QuizNetworkService by lazy {
        retrofit.create(QuizNetworkService::class.java)
    }
}