package com.example.habito.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

@Composable
fun StatsScreen() {
    // Mock data
    val percentDone = 0.7f  // 70%
    val bestStreak = 25
    val completed = 30
    val missed = 5

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Your Stats",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Circle progress
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.size(150.dp)
            ) {
                CircularProgressIndicator(
                    progress = percentDone,
                    strokeWidth = 12.dp,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.fillMaxSize()
                )
                Text(
                    text = "${(percentDone * 100).roundToInt()}%",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Stats boxes
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                StatBox(label = "Best Streak", value = "$bestStreak 🏅")
                StatBox(label = "Completed", value = "$completed ✅")
                StatBox(label = "Missed", value = "$missed ❌")
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Weekly Progress",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Simple horizontal bars for week progress
            val weekProgress = listOf(0.5f, 0.7f, 0.9f, 0.3f, 1f, 0.8f, 0.6f)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                weekProgress.forEach { progress ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(100.dp)
                            .background(
                                color = MaterialTheme.colorScheme.primary.copy(alpha = progress),
                                shape = RoundedCornerShape(8.dp)
                            )
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Top Habit",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.Start)
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(text = "Abs Workout", fontWeight = FontWeight.Bold)
                    Text(text = "Completed 90% this week")
                }
            }
        }
    }
}

@Composable
fun StatBox(label: String, value: String) {
    Card(
        modifier = Modifier.size(100.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = value, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = label, fontSize = 14.sp)
        }
    }
}
