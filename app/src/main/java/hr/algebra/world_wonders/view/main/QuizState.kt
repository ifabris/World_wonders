package hr.algebra.world_wonders.view.main

import hr.algebra.world_wonders.model.Point
import hr.algebra.world_wonders.model.Question

data class QuizState(
    val questions: List<Question> = emptyList(),
    val loading: Boolean = true

)