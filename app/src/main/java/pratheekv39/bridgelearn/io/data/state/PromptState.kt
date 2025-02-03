package pratheekv39.bridgelearn.io.data.state

sealed class PromptState {
    object Idle : PromptState()
    object Loading : PromptState()
    data class Success(val response: String) : PromptState()
    data class Error(val message: String) : PromptState()
}