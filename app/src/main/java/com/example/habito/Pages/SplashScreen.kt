package com.example.habito.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    // Navigate after 2 seconds
    LaunchedEffect(Unit) {
        delay(2000)
        navController.navigate("welcome") {
            popUpTo("splash") { inclusive = true }
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 🔻 Optional: Replace with your logo
            // Image(painter = painterResource(id = R.drawable.ic_logo), contentDescription = "Logo")

            Text(
                text = "Habito",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2196F3)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Small habits, big impact 💪",
                fontSize = 16.sp,
                color = Color.Gray
            )
        }
    }
}
