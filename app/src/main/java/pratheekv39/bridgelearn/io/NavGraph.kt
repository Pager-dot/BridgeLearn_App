package pratheekv39.bridgelearn.io

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

sealed class Screen(
    val route: String,
    val title: String,
    val icon: @Composable () -> Unit
) {
    data object Home : Screen(
        route = "home",
        title = "Home",
        icon = { Icon(Icons.Filled.Home, contentDescription = "Home") }
    )

    data object Quiz : Screen(
        route = "quiz",
        title = "Quiz",
        icon = { Icon(Icons.Filled.Quiz, contentDescription = "Quiz") }
    )

    data object Learn : Screen(
        route = "learn",
        title = "Learn",
        icon = { Icon(Icons.Filled.School, contentDescription = "Learn") }
    )

    data object Community : Screen(
        route = "community",
        title = "Community",
        icon = { Icon(Icons.Filled.Group, contentDescription = "Community") }
    )

    data object Profile : Screen(
        route = "profile",
        title = "Profile",
        icon = { Icon(Icons.Filled.Person, contentDescription = "Profile") }
    )

    data object Subject : Screen(
        route = "subject/{subjectId}",
        title = "Subject",
        icon = { Icon(Icons.Filled.Description, contentDescription = "Subject") }
    ) {
        fun createRoute(subjectId: String) = "subject/$subjectId"
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.Home.route ,
    themeViewModel: ThemeViewModel = viewModel()

) {
    val darkMode by themeViewModel.darkMode.collectAsState()
    val screens = listOf(
        Screen.Home,
        Screen.Quiz,
        Screen.Learn,
        Screen.Community,
        Screen.Profile
    )
    Scaffold(
        bottomBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            // Hide the bottom bar when on the "Interactive" screen
            if (currentRoute != "Interactive") {
                NavigationBar {
                    screens.forEach { screen ->
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    imageVector = when (screen) {
                                        is Screen.Home -> Icons.Filled.Home
                                        is Screen.Quiz -> Icons.Filled.Quiz
                                        is Screen.Learn -> Icons.Filled.School
                                        is Screen.Community -> Icons.Filled.Group
                                        is Screen.Profile -> Icons.Filled.Person
                                        else -> Icons.Filled.Description
                                    },
                                    contentDescription = screen.title,
                                    modifier = Modifier.size(36.dp)
                                        .padding(4.dp),
                                    tint = if (currentRoute == screen.route) MaterialTheme.colorScheme.onSurface
                                    else MaterialTheme.colorScheme.onSurface
                                )
                            },
                            selected = currentRoute == screen.route,
                            onClick = {
                                if (currentRoute != screen.route) {
                                    navController.navigate(screen.route) {
                                        popUpTo(navController.graph.startDestinationId) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            },
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = if (currentRoute == screen.route) {
                                    MaterialTheme.colorScheme.surfaceVariant
                                } else {
                                    Color.Transparent
                                }
                            )
                        )
                    }
                }
            }
        }


    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    onSubjectClick = { subjectId ->
                        navController.navigate(Screen.Subject.createRoute(subjectId))
                    }
                )
            }

            composable(Screen.Quiz.route) {
                QuizScreen()
            }
            composable("Interactive") {
                AcidBaseInteractiveLab(navController)
            }

            composable(Screen.Learn.route) { LearnScreen(navController) }

            composable(Screen.Community.route) { CommunityScreen(navController) }

            composable(Screen.Profile.route) { ProfileScreen(navController, darkMode = darkMode,
                onDarkModeChanged = {isChecked ->
                themeViewModel.setDarkMode(isChecked)})

            }
                composable(
                route = Screen.Subject.route,
                arguments = listOf(
                    navArgument("subjectId") {
                        type = NavType.StringType
                    }
                )
            ) { backStackEntry ->
                val subjectId = backStackEntry.arguments?.getString("subjectId")
                SubjectScreen(
                    subjectId = subjectId ?: "",
                    onNavigateBack = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}