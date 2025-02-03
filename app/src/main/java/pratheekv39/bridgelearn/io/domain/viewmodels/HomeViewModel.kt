package pratheekv39.bridgelearn.io.domain.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pratheekv39.bridgelearn.io.R
import pratheekv39.bridgelearn.io.ui.screens.Subject
import pratheekv39.bridgelearn.io.data.state.PromptState

class HomeViewModel : ViewModel() {
    private val _subjects = MutableStateFlow<List<Subject>>(emptyList())
    val subjects = _subjects.asStateFlow()

    private val _promptState = MutableStateFlow<PromptState>(PromptState.Idle)
    val promptState = _promptState.asStateFlow()

    init {
        // Initialize with dummy data for now
        _subjects.value = listOf(
            Subject("physics", "Physics", "Learn about forces and energy", R.drawable.magnet_straight),
            Subject("chemistry", "Chemistry", "Explore matter and reactions", R.drawable.flask),
            Subject("biology", "Biology", "Study life and living organisms", R.drawable.flower),
            Subject("computer", "Computer", "Learn about different computer tools", R.drawable.math_operations)
        )
    }

    fun sendPrompt(prompt: String) {
        viewModelScope.launch {
            _promptState.value = PromptState.Loading
            try {
                // Simulate API call with delay
                kotlinx.coroutines.delay(1500)
                val response = "This is a simulated response to: $prompt"
                _promptState.value = PromptState.Success(response)
            } catch (e: Exception) {
                _promptState.value = PromptState.Error(e.message ?: "Unknown error")
            }
        }
    }
}