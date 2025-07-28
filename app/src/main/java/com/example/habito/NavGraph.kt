package com.example.habito

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.DisposableEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.habito.Pages.Navigation
import com.example.habito.Navigation.MainScreen
import com.example.habito.viewmodel.AuthViewModel
import com.example.habito.viewmodel.AuthState
import android.util.Log

@Composable
fun AppNavigation(
    authViewModel: AuthViewModel = viewModel(),
    onError: (String) -> Unit = {}
) {
    // Create a single NavController for the entire app
    val navController = rememberNavController()
    val authState by authViewModel.authState.collectAsState()

    // Log the current auth state for debugging
    DisposableEffect(authState) {
        val stateType = when (authState) {
            is AuthState.Authenticated -> "Authenticated"
            is AuthState.Unauthenticated -> "Unauthenticated"
            is AuthState.Loading -> "Loading"
            is AuthState.Error -> "Error: ${(authState as AuthState.Error).message}"
        }
        Log.d("HabitoApp", "Current auth state: $stateType")
        onDispose {}
    }

    try {
        // Pass the shared NavController and ViewModel to child components
        when (authState) {
            is AuthState.Authenticated -> {
                // If user is authenticated, show the main app screen with bottom navigation
                MainScreen(navController = navController)
            }
            else -> {
                // Otherwise, show the login/signup flow
                Navigation(
                    navController = navController,
                    authViewModel = authViewModel,
                    onAuthSuccess = { /* Already handled by the authState observer */ }
                )
            }
        }
    } catch (e: Exception) {
        Log.e("HabitoApp", "Error in AppNavigation", e)
        onError(e.message ?: "Unknown error in navigation")
    }
}
