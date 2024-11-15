package pratheekv39.bridgelearn.io

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun QuizScreen(
    modifier: Modifier = Modifier
) {
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var score by remember { mutableStateOf(0) }
    var showResult by remember { mutableStateOf(false) }

    val questions = remember {
        listOf(
            Question(
                "What is the unit of heat?",
                listOf("Kilowatt", "Celsius", "Joule", "Calorie"),
                correctAnswer = "Joule"
            ),
            Question(
                "Which of the following is an example of heat transfer?",
                listOf(
                    "Electricity flowing through a wire",
                    "Sound waves traveling through the air",
                    "Hot coffee cooling down in a cup",
                    "Light reflecting off a mirror"
                ),
                correctAnswer = "Hot coffee cooling down in a cup"
            )
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (!showResult) {
            // Quiz Questions
            Text(
                text = "Question ${currentQuestionIndex + 1} of ${questions.size}",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            QuestionCard(
                question = questions[currentQuestionIndex],
                onAnswerSelected = { selectedAnswer ->
                    if (selectedAnswer == questions[currentQuestionIndex].correctAnswer) {
                        score++
                    }
                    if (currentQuestionIndex < questions.size - 1) {
                        currentQuestionIndex++
                    } else {
                        showResult = true
                    }
                }
            )
        } else {
            // Quiz Results
            QuizResult(
                score = score,
                totalQuestions = questions.size,
                onRetryClick = {
                    currentQuestionIndex = 0
                    score = 0
                    showResult = false
                }
            )
        }
    }
}

@Composable
private fun QuestionCard(
    question: Question,
    onAnswerSelected: (String) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = question.text,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            question.options.forEach { option ->
                Button(
                    onClick = { onAnswerSelected(option) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                ) {
                    Text(option)
                }
            }
        }
    }
}

@Composable
private fun QuizResult(
    score: Int,
    totalQuestions: Int,
    onRetryClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "$score / $totalQuestions",
            style = MaterialTheme.typography.displayLarge,
            textAlign = TextAlign.Center
        )

        Text(
            text = "Keep Practicing! 📚",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Button(
            onClick = onRetryClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Try Another Quiz")
        }
    }
}
