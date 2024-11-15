package pratheekv39.bridgelearn.io

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LearnViewModel : ViewModel() {
    private val _selectedSubject = MutableStateFlow<LearnSubject?>(null)
    val selectedSubject = _selectedSubject.asStateFlow()

    val subjects = listOf(
        LearnSubject(
            id = "physics",
            name = "Physics",
            description = "Learn about forces and energy",
            learningContent = listOf(
                LearningContent("1", "Introduction to Forces", ContentType.VIDEO, 0.8f),
                LearningContent("2", "Newton's Laws", ContentType.SIMULATION, 0.5f),
                LearningContent("3", "Energy Conservation", ContentType.READING, 0.3f)
            )
        ),
        LearnSubject(
            id = "chemistry",
            name = "Chemistry",
            description = "Explore matter and reactions",
            learningContent = listOf(
                LearningContent("1", "Atomic Structure", ContentType.VIDEO, 0.6f),
                LearningContent("2", "Chemical Bonding", ContentType.SIMULATION, 0.4f),
                LearningContent("3", "Periodic Table", ContentType.READING, 0.2f)
            )
        )
    )

    fun selectSubject(subject: LearnSubject) {
        _selectedSubject.value = subject
    }

    fun clearSelectedSubject() {
        _selectedSubject.value = null
    }
}