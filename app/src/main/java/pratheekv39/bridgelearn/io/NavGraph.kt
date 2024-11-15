package pratheekv39.bridgelearn.io

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
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
    startDestination: String = Screen.Home.route
) {
    val screens = listOf(
        Screen.Home,
        Screen.Quiz,
        Screen.Learn,
        Screen.Community,
        Screen.Profile
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                screens.forEach { screen ->
                    NavigationBarItem(
                        icon = { screen.icon() },
                        label = {
                            Text(
                                text = screen.title,
                                maxLines = 1,
                                softWrap = false
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
                        }
                    )
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

            composable(Screen.Learn.route) { LearnScreen(navController) }

            composable(Screen.Community.route) { CommunityScreen(navController) }

            composable(Screen.Profile.route) { ProfileScreen(navController) }


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