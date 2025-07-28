package com.example.habito.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.*
import androidx.navigation.NavController

@Composable
fun CreateHabitScreen(navController: NavController) {
    var habitName by remember { mutableStateOf("") }
    var goalTimes by remember { mutableStateOf("") }
    var reminderTime by remember { mutableStateOf("") }
    var habitType by remember { mutableStateOf("Build") } // or "Quit"
    var location by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Create New Habit",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = habitName,
                onValueChange = { habitName = it },
                label = { Text("Habit Name") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = goalTimes,
                onValueChange = { if (it.all { char -> char.isDigit() }) goalTimes = it },
                label = { Text("Goal (times per day)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = reminderTime,
                onValueChange = { reminderTime = it },
                label = { Text("Reminder Time (e.g., 08:00 AM)") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(text = "Habit Type")
            Row {
                listOf("Build", "Quit").forEach { type ->
                    Row(
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .clickable { habitType = type },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (habitType == type),
                            onClick = { habitType = type }
                        )
                        Text(text = type)
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = location,
                onValueChange = { location = it },
                label = { Text("Location (optional)") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    // TODO: Save habit logic here, call ViewModel or repo
                    navController.popBackStack() // go back after saving
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text(text = "Save Habit", color = Color.White)
            }
        }
    }
}
