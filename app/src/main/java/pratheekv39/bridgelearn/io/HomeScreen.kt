package pratheekv39.bridgelearn.io

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import javax.security.auth.Subject

@Composable
fun HomeScreen(
    onSubjectClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel()
) {
    val subjects = remember { listOf(
        Subject("physics", "Physics", "Learn about forces and energy", R.drawable.magnet_straight),
        Subject("chemistry", "Chemistry", "Explore matter and reactions", R.drawable.flask),
        Subject("biology", "Biology", "Study life and living organisms", R.drawable.flower),
        Subject("computer", "Computer", "Explore different computer tools", R.drawable.math_operations)
    )}

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(56.dp))

        Text(
            text = "Hello Prateek",
            style = MaterialTheme.typography.headlineMedium,
            fontSize = 56.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Glad to see you here again!",
            style = MaterialTheme.typography.titleLarge,
            fontSize = 32.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "7th Grade Science Topics",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(subjects.chunked(2)) { subjectPair ->
                SubjectCardRow(
                    subjects = subjectPair,
                    onClick = { subject -> onSubjectClick(subject.id) }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Ask your doubts",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        AIPromptCard(
            onPromptSubmit = { prompt ->
                // Handle prompt submission
            }
        )
    }
}