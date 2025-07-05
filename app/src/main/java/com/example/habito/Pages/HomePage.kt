package com.example.habito.Pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.habito.ui.theme.AppColors

data class Habit(val title: String, val description: String, val isCompleted: Boolean = false)

@Composable
fun HomeScreen() {
    val habits = remember { mutableStateListOf(
        Habit("Drink Water", "Stay hydrated and fresh"),
        Habit("Read 10 pages", "Daily reading habit"),
        Habit("Exercise", "15 min of body movement")
    )}

    // For edit dialog
    var habitToEdit by remember { mutableStateOf<Habit?>(null) }

    // For add habit dialog input
    var newHabitTitle by remember { mutableStateOf("") }
    var newHabitDescription by remember { mutableStateOf("") }
    var showAddDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Your Habits", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = AppColors.Blue)
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(habits) { habit ->
                HabitCard(
                    habit = habit,
                    onEdit = { habitToEdit = it },
                    onDelete = { habits.remove(it) },
                    onToggleComplete = { toggledHabit ->
                        val index = habits.indexOf(toggledHabit)
                        if (index != -1) {
                            habits[index] = habits[index].copy(isCompleted = !habits[index].isCompleted)
                        }
                    }
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                newHabitTitle = ""
                newHabitDescription = ""
                showAddDialog = true
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = AppColors.Blue),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text("＋ Add Habit", color = Color.White, fontSize = 16.sp)
        }
    }

    // Add Habit Dialog
    if (showAddDialog) {
        AlertDialog(
            onDismissRequest = { showAddDialog = false },
            title = { Text("Add New Habit") },
            text = {
                Column {
                    OutlinedTextField(
                        value = newHabitTitle,
                        onValueChange = { newHabitTitle = it },
                        label = { Text("Title") },
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = newHabitDescription,
                        onValueChange = { newHabitDescription = it },
                        label = { Text("Description") },
                        maxLines = 3
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    if (newHabitTitle.isNotBlank()) {
                        habits.add(
                            Habit(
                                title = newHabitTitle.trim(),
                                description = newHabitDescription.trim(),
                                isCompleted = false
                            )
                        )
                        showAddDialog = false
                    }
                }) {
                    Text("Add")
                }
            },
            dismissButton = {
                TextButton(onClick = { showAddDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }

    // Edit Habit Dialog
    habitToEdit?.let { habit ->
        var editedTitle by remember { mutableStateOf(habit.title) }
        var editedDescription by remember { mutableStateOf(habit.description) }

        AlertDialog(
            onDismissRequest = { habitToEdit = null },
            title = { Text("Edit Habit") },
            text = {
                Column {
                    OutlinedTextField(
                        value = editedTitle,
                        onValueChange = { editedTitle = it },
                        label = { Text("Title") },
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = editedDescription,
                        onValueChange = { editedDescription = it },
                        label = { Text("Description") },
                        maxLines = 3
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    if (editedTitle.isNotBlank()) {
                        val index = habits.indexOf(habit)
                        if (index != -1) {
                            habits[index] = habit.copy(
                                title = editedTitle.trim(),
                                description = editedDescription.trim()
                            )
                        }
                        habitToEdit = null
                    }
                }) {
                    Text("Save")
                }
            },
            dismissButton = {
                TextButton(onClick = { habitToEdit = null }) {
                    Text("Cancel")
                }
            }
        )
    }
}
