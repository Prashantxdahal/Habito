package com.example.habito.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import com.example.habito.components.HabitCard
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*

data class Habit(
    val id: Int,
    val name: String,
    val progress: Int,
    val isDone: Boolean
)

@Composable
fun HomeScreen(navController: NavController) {
    val userName = "Prashant"

    val habits = remember {
        listOf(
            Habit(1, "Drink Water", 45, false),
            Habit(2, "Abs Workout", 100, true),
            Habit(3, "Go for a Walk", 40, false)
        )
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White // Pure white background here
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Hi, $userName 👋 Let’s crush it today!",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2196F3)
            )

            Spacer(modifier = Modifier.height(12.dp))

            WeekDaysRow()

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Your Habits",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(habits) { habit ->
                    HabitCard(habit = habit)
                }
            }
        }

        FloatingActionButton(
            onClick = { navController.navigate("createHabit") },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd),
            containerColor = Color(0xFF2196F3),
            contentColor = Color.White
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add Habit")
        }
    }
}

@Composable
fun WeekDaysRow() {
    val daysOfWeek = DayOfWeek.values()
    val today = LocalDate.now().dayOfWeek

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(daysOfWeek) { day ->
            val isToday = day == today
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        color = if (isToday) Color(0xFF2196F3) else Color.LightGray,
                        shape = MaterialTheme.shapes.small
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = day.getDisplayName(TextStyle.SHORT, Locale.getDefault()),
                    color = if (isToday) Color.White else Color.Black,
                    fontWeight = if (isToday) FontWeight.Bold else FontWeight.Normal
                )
            }
        }
    }
}
