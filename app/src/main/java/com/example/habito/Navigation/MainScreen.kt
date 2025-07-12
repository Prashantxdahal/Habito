package com.example.habito.Navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.habito.Pages.*

sealed class BottomNavItem(val route: String, val icon: ImageVector, val title: String) {
    object Home : BottomNavItem("home", Icons.Default.Home, "Home")
    object Progress : BottomNavItem("progress", Icons.Default.BarChart, "Progress")
    object Profile : BottomNavItem("profile", Icons.Default.Person, "Profile")
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    // ✅ This is your shared habit list (state)
    val habits = remember {
        mutableStateListOf(
            Habit("Drink Water", "Stay hydrated"),
            Habit("Read 10 pages", "Build daily reading habit")
        )
    }

    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = Color.White) {
                val navBackStackEntry = navController.currentBackStackEntryAsState().value
                val currentRoute = navBackStackEntry?.destination?.route

                listOf(BottomNavItem.Home, BottomNavItem.Progress, BottomNavItem.Profile).forEach { item ->
                    NavigationBarItem(
                        selected = currentRoute == item.route,
                        onClick = {
                            if (currentRoute != item.route) {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        },
                        icon = { Icon(item.icon, contentDescription = item.title) },
                        label = { Text(item.title) }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Home.route) {
                HomeScreen(
                    habits = habits,
                    onToggleComplete = { toggledHabit ->
                        val index = habits.indexOf(toggledHabit)
                        if (index != -1) {
                            habits[index] = habits[index].copy(isCompleted = !habits[index].isCompleted)
                        }
                    },
                    navController = navController
                )
            }

            composable("add_habit") {
                AddHabitScreen(navController = navController) { newHabit ->
                    habits.add(newHabit)
                }
            }

            composable(BottomNavItem.Progress.route) {
                ProgressScreen(habits = habits)
            }

            composable(BottomNavItem.Profile.route) {
                ProfileScreen()
            }
        }
    }
}
