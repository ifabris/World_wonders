package hr.algebra.world_wonders.view.main

import android.graphics.Color
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import hr.algebra.world_wonders.model.Question
import kotlinx.coroutines.Delay
import kotlin.random.Random


@Composable
fun QuizScreen(modifier: Modifier = Modifier, quizState: QuizState) {

    var selectedOption = remember { mutableStateOf("") }
    if (quizState.loading) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        val questions = quizState.questions
        val myRandomValues = Random.nextInt(0, 9)
        val question = questions[myRandomValues]

        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            questionRow(question = question, selectedOption = selectedOption)
        }
    }
}

@Composable
fun questionRow(question: Question, selectedOption: MutableState<String>) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = question.question,
            style = TextStyle(
                fontWeight = FontWeight.Bold
            )
        )
        Box {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(question.image)
                    .crossfade(true)
                    .build(),
                contentDescription = "Picture",
                contentScale = ContentScale.FillBounds,
            )
        }
        optionButton("A", question.A, selectedOption, "A", question.answer)
        optionButton("B", question.B, selectedOption, "B", question.answer)
        optionButton("C", question.C, selectedOption, "C", question.answer)
        optionButton("D", question.D, selectedOption, "D", question.answer)
    }
}

@Composable
fun optionButton(
    option: String,
    optionText: String,
    selectedOption: MutableState<String>,
    optionValue: String,
    answer: String
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = {
                selectedOption.value = optionValue
            }),
        shape = RoundedCornerShape(8.dp),
        color = MaterialTheme.colors.surface
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "$option. $optionText",
            )
            if (selectedOption.value == optionValue && selectedOption.value == answer) {
                Text(
                    text = "Correct!",
                )
            } else if (selectedOption.value == optionValue) {
                Text(
                    text = "Wrong!")
            }

        }
    }

}

