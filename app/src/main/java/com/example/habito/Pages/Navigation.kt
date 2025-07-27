package com.example.habito.Pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.habito.Models.Habit

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(
                onLoginSuccess = { navController.navigate("home") },
                onNavigateToSignup = { navController.navigate("signup") },
                onNavigateToForgotPassword = { /* TODO: Navigate to ForgotPasswordScreen */ }
            )
        }
        composable("signup") {
            SignupScreen(onNavigateToLogin = { navController.navigate("login") })
        }
        composable("home") {
            // Provide a sample list and lambdas for preview/testing
            val habits = remember {
                mutableStateListOf(
                    Habit("Drink Water", "Stay hydrated and fresh"),
                    Habit("Read 10 pages", "Daily reading habit"),
                    Habit("Exercise", "15 min of body movement")
                )
            }
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
                        habits[index] = habits[index].copy(title = newTitle, description = newDesc)
                    }
                },
                onDeleteHabit = { habit ->
                    habits.remove(habit)
                },
                onAddHabit = { newHabit ->
                    habits.add(newHabit)
                }
            )
        }
        // Add HomeScreen and AddHabitScreen navigation as needed
    }
}
