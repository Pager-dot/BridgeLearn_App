package pratheekv39.bridgelearn.io.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import pratheekv39.bridgelearn.io.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectScreen(
    subjectId: String,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val subject = remember(subjectId) {
        when (subjectId) {
            "physics" -> Subject(
                "physics",
                "Physics",
                "Learn about forces and energy",
                R.drawable.magnet_straight
            )
            "chemistry" -> Subject(
                "chemistry",
                "Chemistry",
                "Explore matter and reactions",
                R.drawable.flask
            )
            "biology" -> Subject(
                "biology",
                "Biology",
                "Learn about human body and animals",
                R.drawable.flower
            )
            "computer" -> Subject(
                "computer",
                "Computer",
                "Explore different types of computer tools",
                R.drawable.math_operations
            )
            else -> Subject(
                "unknown",
                "Unknown Subject",
                "Subject not found",
                R.drawable.ic_launcher_foreground
            )
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(subject.name) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "Overview",
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.White,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Text(
                            text = subject.description,
                            color = Color.White,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }

            // Add more content specific to each subject
            item {
                Text(
                    text = "Topics",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            // Example topics
            items(5) { index ->
                TopicCard(
                    title = "Topic ${index + 1}",
                    description = "Description for topic ${index + 1}"
                )
            }
        }
    }
}

@Composable
private fun TopicCard(
    title: String,
    description: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = Color.White,
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
            )
        }
    }
}

// Data classes needed for the screens
data class Subject(
    val id: String,
    val name: String,
    val description: String,
    val drawableResId: Int
)

data class Question(
    val text: String,
    val options: List<String>,
    val correctAnswer: String
)