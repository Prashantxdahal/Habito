package com.example.habito.Navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val icon: ImageVector,
    val title: String
) {
    object Home : BottomNavItem("home", Icons.Default.Home, "Home")
    object Progress : BottomNavItem("progress", Icons.Default.List, "Progress")
    object Profile : BottomNavItem("profile", Icons.Default.Person, "Profile")
}
