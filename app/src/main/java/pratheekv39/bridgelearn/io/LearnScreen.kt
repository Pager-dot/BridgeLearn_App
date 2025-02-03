package pratheekv39.bridgelearn.io

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@Composable
fun LearnScreen(
    navController: NavController,
    viewModel: LearnViewModel = viewModel()
) {
    val selectedSubject by viewModel.selectedSubject.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        if (selectedSubject == null) {
            LearnDashboard(
                subjects = viewModel.subjects,
                onSubjectSelect = { viewModel.selectSubject(it) }
            )
        } else {
            SubjectLearningContent(
                subject = selectedSubject!!,
                onBackPress = { viewModel.clearSelectedSubject() },
                navController
            )
        }
    }
}

@Composable
fun LearnDashboard(
    subjects: List<LearnSubject>,
    onSubjectSelect: (LearnSubject) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = "Interactive Learning",
                style = MaterialTheme.typography.headlineMedium
            )
        }

        items(subjects) { subject ->
            SubjectCard(
                subject = subject,
                onClick = { onSubjectSelect(subject) }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectCard(
    subject: LearnSubject,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon for the subject
            Icon(
                painter = painterResource(id = subject.drawableResId), // Pass the icon resource ID
                contentDescription = "${subject.name} Icon",
                tint = Color.White, // Optional: Set icon color
                modifier = Modifier
                    .size(48.dp) // Adjust size as needed
                    .padding(end = 16.dp) // Add spacing between the icon and text
            )

            Column(
                modifier = Modifier.weight(1f) // Ensures text takes the remaining space
            ) {
                Text(
                    text = subject.name,
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = subject.description,
                    color = Color.White,
                    style = MaterialTheme.typography.bodyMedium
                )
                LinearProgressIndicator(
                    progress = subject.learningContent.map { it.progress }.average().toFloat(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    color = Color.White
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectLearningContent(
    subject: LearnSubject,
    onBackPress: () -> Unit,
    navController: NavController
) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text(subject.name) },
            navigationIcon = {
                IconButton(onClick = onBackPress) {
                    Icon(Icons.Default.ArrowBack, "Back")
                }
            }
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Group content by type
            val groupedContent = subject.learningContent.groupBy { it.type.name }

            groupedContent.forEach { (type, contents) ->
                // Add a heading for each type
                item {
                    Text(
                        text = type, // Heading for content type
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    )
                }

                // Add content items under each type
                items(contents) { content ->
                    LearningContentCard(content, navController,subject)
                }
            }
        }
    }
}

@Composable
fun LearningContentCard(content: LearningContent, navController: NavController, subject: LearnSubject) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                when {
                    content.title == "Acids and Base Simulator" -> navController.navigate("Interactive")
                    content.title == "Pendulum Simulator" -> navController.navigate("Pendulum")
                    content.title == "Spring Simulator" -> navController.navigate("Spring")
                    content.title == "Periodic Table" -> navController.navigate("PeriodicTable")
                    content.title == "Energy Conservation" -> navController.navigate("EnergyConservation")
                }
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(
                    id = when (content.type.name) {
                        "READING" -> R.drawable.book_open_text
                        "SIMULATION" -> R.drawable.waves
                        "VIDEO" -> R.drawable.monitor_play
                        else -> R.drawable.ic_launcher_foreground
                    }
                ),
                contentDescription = "${content.type.name} Icon",
                tint = Color.White,
                modifier = Modifier
                    .size(48.dp)
                    .padding(end = 16.dp)
            )

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = content.title,
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium
                )
                LinearProgressIndicator(
                    progress = content.progress,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                )
            }
        }
    }
}