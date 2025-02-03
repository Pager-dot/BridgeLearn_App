package pratheekv39.bridgelearn.io.ui.screens.community

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import pratheekv39.bridgelearn.io.theme.AfacadFontFamily
import java.time.Instant
import java.time.temporal.ChronoUnit

// Data Classes
data class ForumPost(
    val id: String,
    val title: String,
    val content: String,
    val author: String,
    val timestamp: Instant,
    val likes: Int = 0,
    val replies: Int = 0,
    val tags: List<String> = emptyList()
)

data class StudyGroup(
    val id: String,
    val name: String,
    val description: String,
    val memberCount: Int,
    val subject: String,
    val isJoined: Boolean = false
)

sealed class CommunityTab(val title: String) {
    object Forum : CommunityTab("Forum")
    object StudyGroups : CommunityTab("Study Groups")
    object LiveSessions : CommunityTab("Live Sessions") // TODO: Future implementation
    object Mentorship : CommunityTab("Mentorship") // TODO: Future implementation
}

// ViewModel
class CommunityViewModel : ViewModel() {
    private val _selectedTab = MutableStateFlow<CommunityTab>(CommunityTab.Forum)
    val selectedTab = _selectedTab.asStateFlow()

    // Sample data - In real app, this would come from a repository
    @RequiresApi(Build.VERSION_CODES.O)
    val forumPosts = listOf(
        ForumPost(
            id = "1",
            title = "Help with Newton's Laws",
            content = "I'm struggling to understand the third law...",
            author = "PhysicsStudent",
            timestamp = Instant.now().minus(2, ChronoUnit.HOURS),
            likes = 5,
            replies = 3,
            tags = listOf("physics", "newton-laws")
        ),
        ForumPost(
            id = "2",
            title = "Chemistry Lab Safety Tips",
            content = "Here are some important safety guidelines...",
            author = "ChemTeacher",
            timestamp = Instant.now().minus(1, ChronoUnit.DAYS),
            likes = 12,
            replies = 7,
            tags = listOf("chemistry", "lab-safety")
        )
    )

    val studyGroups = listOf(
        StudyGroup(
            id = "1",
            name = "Physics Study Circle",
            description = "Weekly discussions on physics concepts",
            memberCount = 25,
            subject = "Physics"
        ),
        StudyGroup(
            id = "2",
            name = "Chemistry Enthusiasts",
            description = "Daily problem solving and discussions",
            memberCount = 30,
            subject = "Chemistry"
        )
    )

    fun selectTab(tab: CommunityTab) {
        _selectedTab.value = tab
    }
}

// Main Screen
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CommunityScreen(
    navController: NavController,
    viewModel: CommunityViewModel = viewModel()
) {
    val selectedTab by viewModel.selectedTab.collectAsState()
    val tabs = listOf(
        CommunityTab.Forum,
        CommunityTab.StudyGroups,
        CommunityTab.LiveSessions,
        CommunityTab.Mentorship
    )

    Column(modifier = Modifier.fillMaxSize()) {
        // Tabs
        ScrollableTabRow(
            selectedTabIndex = tabs.indexOf(selectedTab),
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[tabs.indexOf(selectedTab)]),
                    color = MaterialTheme.colorScheme.surfaceVariant // Change this to your desired color
                )
            }
        ) {
            tabs.forEach { tab ->
                Tab(
                    selected = selectedTab == tab,
                    onClick = { viewModel.selectTab(tab) },
                    text = { Text(tab.title,
                        style = TextStyle(fontFamily = AfacadFontFamily,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp)
                    ) }
                )
            }
        }

        // Content
        when (selectedTab) {
            is CommunityTab.Forum -> ForumSection(
                posts = viewModel.forumPosts,
                onPostClick = { /* TODO: Navigate to post detail */ }
            )
            is CommunityTab.StudyGroups -> StudyGroupsSection(
                groups = viewModel.studyGroups,
                onGroupClick = { /* TODO: Navigate to group detail */ }
            )
            is CommunityTab.LiveSessions -> ComingSoonSection("Live Sessions")
            is CommunityTab.Mentorship -> ComingSoonSection("Mentorship")
        }
    }
}

@Composable
fun ForumSection(
    posts: List<ForumPost>,
    onPostClick: (ForumPost) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        // Search and filter - TODO: Implement
        OutlinedTextField(
            value = "",
            onValueChange = { /* TODO */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            placeholder = { Text("Search discussions...") },
            trailingIcon = { Icon(Icons.Default.Search, null) }
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(posts) { post ->
                ForumPostCard(post = post, onClick = { onPostClick(post) })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForumPostCard(
    post: ForumPost,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = post.title,
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = post.content,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "by ${post.author}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.ThumbUp,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = Color.White
                        )
                        Text(
                            text = "${post.likes}",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.Comment,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = Color.White
                        )
                        Text(
                            text = "${post.replies}",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun StudyGroupsSection(
    groups: List<StudyGroup>,
    onGroupClick: (StudyGroup) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(groups) { group ->
            StudyGroupCard(group = group, onClick = { onGroupClick(group) })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudyGroupCard(
    group: StudyGroup,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = group.name,
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = group.description,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${group.memberCount} members",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White
                )
                Button(
                    onClick = { /* TODO: Join/Leave group */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (group.isJoined)
                            MaterialTheme.colorScheme.surfaceVariant
                        else
                            Color.White
                    )
                ) {
                    Text(if (group.isJoined) "Joined" else "Join",
                        fontFamily = AfacadFontFamily,
                        fontWeight = FontWeight.Medium,
                        color = if (group.isJoined) Color.White else Color.Black)
                }
            }
        }
    }
}

@Composable
fun ComingSoonSection(feature: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                Icons.Default.Construction,
                contentDescription = null,
                modifier = Modifier.size(48.dp),
                tint = MaterialTheme.colorScheme.surfaceVariant
            )
            Text(
                text = "$feature Coming Soon!",
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = "This feature is under development.\nWant to contribute? Check our GitHub!",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }
    }
}