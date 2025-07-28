package com.example.habito.viewmodel

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.habito.pages.*
import com.example.habito.navigation.MainScreen

@Composable
fun AppNavigation(navController: NavHostController) {
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
            MainScreen() // already contains home, stats, profile with bottom nav
        }

        composable("createHabit") {
            CreateHabitScreen(navController)
        }
    }
}
