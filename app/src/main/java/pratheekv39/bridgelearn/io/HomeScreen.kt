package pratheekv39.bridgelearn.io

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import javax.security.auth.Subject

@Composable
fun HomeScreen(
    onSubjectClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel()
) {
    val subjects = remember { listOf(
        Subject("physics", "Physics", "Learn about forces and energy"),
        Subject("chemistry", "Chemistry", "Explore matter and reactions"),
        Subject("biology", "Biology", "Study life and living organisms"),
        Subject("environmental", "Environmental Science", "Understand our planet")
    )}

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Interactive Learning Platform",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        AIPromptCard(
            onPromptSubmit = { prompt ->
                // Handle prompt submission
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "7th Grade Science Topics",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(subjects) { subject ->
                SubjectCard(
                    subject = subject,
                    onClick = { onSubjectClick(subject.id) }
                )
            }
        }
    }
}