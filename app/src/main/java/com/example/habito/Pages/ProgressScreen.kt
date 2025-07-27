package com.example.habito.Pages

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.habito.ui.theme.AppColors

@Composable
fun ProgressScreen(habits: List<Habit>) {
    val total = habits.size
    val completed = habits.count { it.isCompleted }
    val progress = if (total > 0) completed.toFloat() / total else 0f

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Habit Progress",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = AppColors.Blue
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "You've completed $completed out of $total habits",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(16.dp))

        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp),
            color = AppColors.Blue
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = when {
                progress == 1f -> "🔥 You're crushing it today!"
                progress >= 0.5f -> "👏 Great going, keep pushing!"
                progress > 0f -> "💪 Let's get a few more done!"
                else -> "⚡ Time to start tracking those habits!"
            },
            fontSize = 16.sp,
            fontWeight = FontWeight.Light
        )
    }
}
