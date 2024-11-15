package pratheekv39.bridgelearn.io

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

// Data Classes
data class UserProfile(
    val id: String,
    val name: String,
    val email: String,
    val grade: String,
    val avatarUrl: String? = null,
    val bio: String = "",
    val completedLessons: Int = 0,
    val quizzesTaken: Int = 0,
    val averageScore: Float = 0f,
    val learningStreak: Int = 0,
    val preferences: UserPreferences = UserPreferences()
)

data class UserPreferences(
    val isDarkMode: Boolean = false,
    val notificationsEnabled: Boolean = true,
    val language: String = "English",
    val studyReminderTime: String = "18:00",
    val accessibilityOptions: AccessibilityOptions = AccessibilityOptions()
)

data class AccessibilityOptions(
    val fontSize: Float = 1.0f,
    val highContrast: Boolean = false,
    val screenReader: Boolean = false
)

data class Achievement(
    val id: String,
    val title: String,
    val description: String,
    val icon: @Composable () -> Unit,
    val isUnlocked: Boolean,
    val progress: Float = 0f
)

// ViewModel
class ProfileViewModel : ViewModel() {
    private val _userProfile = MutableStateFlow(
        UserProfile(
            id = "1",
            name = "Student Name",
            email = "student@example.com",
            grade = "7th Grade",
            bio = "Passionate about learning science!"
        )
    )
    val userProfile = _userProfile.asStateFlow()

    val achievements = listOf(
        Achievement(
            id = "1",
            title = "Quick Learner",
            description = "Complete 5 lessons in a day",
            icon = { Icon(Icons.Default.Star, null) },
            isUnlocked = true,
            progress = 1f
        ),
        Achievement(
            id = "2",
            title = "Quiz Master",
            description = "Score 100% in 3 quizzes",
            icon = { Icon(Icons.Default.EmojiEvents, null) },
            isUnlocked = false,
            progress = 0.6f
        )
    )

    // TODO: Implement profile update functionality
    fun updateProfile(name: String, bio: String) {
        // Update profile logic
    }

    // TODO: Implement preference update functionality
    fun updatePreferences(preferences: UserPreferences) {
        // Update preferences logic
    }
}

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = viewModel()
) {
    val userProfile by viewModel.userProfile.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        // Profile Header
        item {
            ProfileHeader(userProfile)
        }

        // Stats Section
        item {
            StatsSection(userProfile)
        }

        // Achievements Section
        item {
            Text(
                text = "Achievements",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }
        item {
            AchievementsSection(viewModel.achievements)
        }

        // Settings Section
        item {
            SettingsSection(userProfile.preferences)
        }
    }
}

@Composable
fun ProfileHeader(profile: UserProfile) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Profile Image
        Surface(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape),
            color = MaterialTheme.colorScheme.primaryContainer
        ) {
            if (profile.avatarUrl != null) {
                // TODO: Implement image loading
                // Currently just showing a placeholder
                Icon(
                    Icons.Default.Person,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxSize()
                )
            } else {
                Icon(
                    Icons.Default.Person,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxSize()
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = profile.name,
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = profile.grade,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        if (profile.bio.isNotEmpty()) {
            Text(
                text = profile.bio,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        // Edit Profile Button
        OutlinedButton(
            onClick = { /* TODO: Implement edit profile */ },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Icon(Icons.Default.Edit, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Edit Profile")
        }
    }
}

@Composable
fun StatsSection(profile: UserProfile) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Learning Stats",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                StatItem(
                    value = profile.completedLessons.toString(),
                    label = "Lessons",
                    icon = Icons.Default.School
                )
                StatItem(
                    value = profile.quizzesTaken.toString(),
                    label = "Quizzes",
                    icon = Icons.Default.Quiz
                )
                StatItem(
                    value = "${(profile.averageScore * 100).toInt()}%",
                    label = "Avg. Score",
                    icon = Icons.Default.Grade
                )
            }
        }
    }
}

@Composable
fun StatItem(
    value: String,
    label: String,
    icon: ImageVector
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
        Text(
            text = value,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
fun AchievementsSection(achievements: List<Achievement>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            achievements.forEach { achievement ->
                AchievementCard(
                    achievement = achievement,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun AchievementCard(
    achievement: Achievement,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            achievement.icon()
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = achievement.title,
                style = MaterialTheme.typography.titleSmall
            )
            LinearProgressIndicator(
                progress = achievement.progress,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )
        }
    }
}

@Composable
fun SettingsSection(preferences: UserPreferences) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Settings",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Theme Setting
            SettingItem(
                title = "Dark Mode",
                icon = Icons.Default.DarkMode,
                trailing = {
                    Switch(
                        checked = preferences.isDarkMode,
                        onCheckedChange = { /* TODO: Implement theme change */ }
                    )
                }
            )

            // Notifications Setting
            SettingItem(
                title = "Notifications",
                icon = Icons.Default.Notifications,
                trailing = {
                    Switch(
                        checked = preferences.notificationsEnabled,
                        onCheckedChange = { /* TODO: Implement notifications toggle */ }
                    )
                }
            )

            // Language Setting (TODO: Implement)
            SettingItem(
                title = "Language",
                icon = Icons.Default.Language,
                trailing = {
                    Text(preferences.language)
                }
            )

            // Accessibility Options (TODO: Implement)
            SettingItem(
                title = "Accessibility",
                icon = Icons.Default.Accessibility,
                trailing = {
                    Icon(Icons.Default.ChevronRight, null)
                }
            )
        }
    }
}

@Composable
fun SettingItem(
    title: String,
    icon: ImageVector,
    trailing: @Composable () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(title)
        }
        trailing()
    }
}