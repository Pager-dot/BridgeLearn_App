package pratheekv39.bridgelearn.io.ui.screens.quiz

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import pratheekv39.bridgelearn.io.R
import pratheekv39.bridgelearn.io.theme.AfacadFontFamily
import pratheekv39.bridgelearn.io.ui.screens.Question

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
                text = "Test your knowledge",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Question ${currentQuestionIndex + 1} of ${questions.size}",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

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

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Subject-Wise Quizzes",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item { SubjectCardQuiz("Physics", R.drawable.magnet_straight, Color(0xFFBBDEFB)) }  // Light Blue
            item { SubjectCardQuiz("Chemistry", R.drawable.flask, Color(0xFFFFCDD2)) } // Light Red
            item { SubjectCardQuiz("Biology", R.drawable.flower, Color(0xFFC8E6C9)) }   // Light Green
            item { SubjectCardQuiz("Computer", R.drawable.math_operations, Color(0xFFD1C4E9)) } // Light Purple
        }
    }
}

@Composable
fun SubjectCardQuiz(subject: String, iconResId: Int, backgroundColor: Color) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(84.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = iconResId),
                contentDescription = "$subject Icon",
                modifier = Modifier
                    .size(48.dp)
                    .padding(end = 16.dp),
                tint = Color.Unspecified // Use the original icon color
            )
            Text(
                text = subject,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black
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
                color = Color.White,
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
                        containerColor = Color.White,
                        contentColor = Color.Black
                    )
                ) {
                    Text(option, fontFamily = AfacadFontFamily)
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
