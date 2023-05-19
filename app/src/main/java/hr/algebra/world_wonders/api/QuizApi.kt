package hr.algebra.world_wonders.api

import hr.algebra.world_wonders.model.Question
import retrofit2.http.GET

interface QuizApi {
    @GET("/raw/aJitT3Kx")
    suspend fun getQuestions(): Map<String, Question>
}