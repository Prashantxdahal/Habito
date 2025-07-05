package com.example.habito.Pages

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

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
            HomeScreen()
        }
        // Add HomeScreen and AddHabitScreen navigation as needed
    }
}
