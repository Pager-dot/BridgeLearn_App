// AIPromptCard.kt
package pratheekv39.bridgelearn.io.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme

@Composable
fun AIPromptCard(
    onPromptSubmit: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var text by remember { mutableStateOf("") }

    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            OutlinedTextField(
                value = text,
                onValueChange = {
                    text = it
                },
                label = { Text("Ask your question") },
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}