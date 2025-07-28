package com.example.habito.viewmodel

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.habito.pages.*
import com.example.habito.navigation.MainScreen

@Composable
fun AppNavigation(onError: (String) -> Unit) {
    val navController = rememberNavController()

    try {
        NavHost(
            navController = navController,
            startDestination = "splash"
        ) {
            composable("splash") {
                SplashScreen(navController)
            }
            composable("welcome") {
                WelcomeScreen(navController)
            }
            composable("login") {
                LoginScreen(navController)
            }
            composable("signup") {
                SignupScreen(navController)
            }
            composable("main") {
                MainScreen()
            }
            composable("createHabit") {
                CreateHabitScreen(navController)
            }
        }
    } catch (e: Exception) {
        onError("Navigation error: ${e.localizedMessage ?: "Unknown error"}")
    }
}
