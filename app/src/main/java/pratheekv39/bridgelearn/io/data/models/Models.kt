package pratheekv39.bridgelearn.io.data.models

import androidx.compose.ui.graphics.vector.ImageVector

data class LearningContent(
    val id: String,
    val title: String,
    val type: ContentType,
    val progress: Float = 0f
)

data class LearningResource(
    val id: String,
    val title: String,
    val description: String,
    val icon: ImageVector
)

enum class ContentType {
    VIDEO,
    SIMULATION,
    READING,
    PRACTICE
}

    data class LearnSubject(
        val id: String,
        val name: String,
        val drawableResId: Int,
        val description: String,
        val learningContent: List<LearningContent>
    )