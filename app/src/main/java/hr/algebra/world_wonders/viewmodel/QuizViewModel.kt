package hr.algebra.world_wonders.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.algebra.world_wonders.repository.PointsRepository
import hr.algebra.world_wonders.repository.QuestionRepository
import hr.algebra.world_wonders.view.main.MapState
import hr.algebra.world_wonders.view.main.QuizState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val repository: QuestionRepository
) : ViewModel(){
    private val _quizState = mutableStateOf(QuizState())

    val  quizState: State<QuizState>
        get() = _quizState

    private val errorHandler = CoroutineExceptionHandler {_, e ->
        Log.e("QUIZ_VIEWMODEL", e.toString(), e)
    }

    init {
        viewModelScope.launch (errorHandler){
            _quizState.value = _quizState.value.copy(
                questions = repository.getQuestions(),
                loading = false
            )
        }
    }
}