// SubjectCard.kt
package pratheekv39.bridgelearn.io.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import pratheekv39.bridgelearn.io.R
import pratheekv39.bridgelearn.io.ui.screens.Subject

@Composable
fun SubjectCard(
    subject: Subject,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val customLightBlue = MaterialTheme.colorScheme.background

    Card(
        modifier = modifier
            .fillMaxWidth(0.5f) // Take up half the width
            .clickable(onClick = onClick)
            .padding(vertical = 4.dp, horizontal = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant // Change this to your desired color
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Surface(
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colorScheme.surfaceVariant,
                modifier = Modifier.size(40.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Icon(
                        painter = painterResource(id = subject.drawableResId), // Change this to the icon related to the subject
                        contentDescription = null,
                        tint = Color.White,
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = subject.name,
                style = MaterialTheme.typography.titleMedium,
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun SubjectCardRow(subjects: List<Subject>, onClick: (Subject) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        subjects.take(2).forEach { subject ->
            SubjectCard(
                subject = subject,
                onClick = { onClick(subject) },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

// Preview
@Preview(showBackground = true)
@Composable
fun SubjectCardPreview() {
    MaterialTheme {
        SubjectCard(
            subject = Subject(
                id = "physics",
                name = "Physics",
                description = "Learn about forces, energy, and the fundamental laws that govern the universe.",
                R.drawable.magnet_straight

            ),
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AIPromptCardPreview() {
    MaterialTheme {
        AIPromptCard(
            onPromptSubmit = {}
        )
    }
}