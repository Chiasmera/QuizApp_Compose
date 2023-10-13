package com.chiasmera.quizudfordring_compose.service

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_CATEGORY_URL = "https://opentdb.com/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_CATEGORY_URL).build()

/**
 * Interface for servide class, containing methods to call the API
 */
interface QuizNetworkService {

    /**
     * Fetches a CategoryResult object, containing a list of all categories from the API
     */
    @GET("api_category.php")
    suspend fun getCategoryList(): CategoryResult

    /**
     * Fetches a QuestionsResult object, containing the indicated amount of questions with the given difficulty and category
     */
    @GET("api.php")
    suspend fun getQuestionsForCategory(
        @Query("amount") amount: Int,
        @Query("category") categoryID: Int,
        @Query("difficulty") difficulty: String
    ): QuestionsResult

    /**
     * Fetches the amount of questions for each difficulty in the given category
     */
    @GET("api_count.php")
    suspend fun getNumberOfQuestionsForCategory(
        @Query("category") categoryID: Int
    ): CategoryCount
}

/**
 * Singleton object for using retrofit to call api.
 * Should be incorporated into the app structure at a later date, using unidirectional data flow, instead of using a singleton pattern.
 */
object QuizAPI {
    val retrofitService: QuizNetworkService by lazy {
        retrofit.create(QuizNetworkService::class.java)
    }
}