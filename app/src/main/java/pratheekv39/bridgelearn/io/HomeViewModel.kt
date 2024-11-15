package pratheekv39.bridgelearn.io

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _subjects = MutableStateFlow<List<Subject>>(emptyList())
    val subjects = _subjects.asStateFlow()

    private val _promptState = MutableStateFlow<PromptState>(PromptState.Idle)
    val promptState = _promptState.asStateFlow()

    init {
        // Initialize with dummy data for now
        _subjects.value = listOf(
            Subject("physics", "Physics", "Learn about forces and energy"),
            Subject("chemistry", "Chemistry", "Explore matter and reactions"),
            Subject("biology", "Biology", "Study life and living organisms"),
            Subject("environmental", "Environmental Science", "Understand our planet")
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