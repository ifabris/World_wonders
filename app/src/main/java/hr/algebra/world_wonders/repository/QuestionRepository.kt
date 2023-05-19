package hr.algebra.world_wonders.repository

import hr.algebra.world_wonders.api.QuizApi
import hr.algebra.world_wonders.model.Question
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QuestionRepository @Inject constructor(
    private val quizApi: QuizApi
) {
    suspend fun getQuestions(): List<Question> {
        return withContext(Dispatchers.IO) {
            quizApi.getQuestions().values.toList()
        }
    }
}