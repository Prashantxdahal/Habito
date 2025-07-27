package com.example.habito.Navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.*
import com.example.habito.Pages.*
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun MainScreen() {
    val navController = rememberNavController()


    var userName by rememberSaveable { mutableStateOf("User") }

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


                listOf(
                    BottomNavItem.Home,
                    BottomNavItem.Progress,
                    BottomNavItem.Profile
                ).forEach { item ->
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
                    navController = navController,
                    onToggleComplete = { toggledHabit ->
                        val index = habits.indexOf(toggledHabit)
                        if (index != -1) {
                            habits[index] = habits[index].copy(isCompleted = !habits[index].isCompleted)
                        }
                    },
                    onEditHabit = { habit, newTitle, newDesc ->
                        val index = habits.indexOf(habit)
                        if (index != -1) {
                            habits[index] = habit.copy(title = newTitle, description = newDesc)
                        }
                    },
                    onDeleteHabit = { habits.remove(it) },
                    onAddHabit = { habits.add(it) }
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
                ProfileScreen(
                    userName = userName,
                    onUserNameChange = { userName = it },
                    onClearData = { habits.clear() }
                )
            }
        }
    }
}
