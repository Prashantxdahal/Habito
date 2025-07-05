package com.example.habito.Pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import com.example.habito.ui.theme.AppColors.Blue

@Composable
fun HabitCard(
    habit: Habit,
    onEdit: (Habit) -> Unit,
    onDelete: (Habit) -> Unit,
    onToggleComplete: (Habit) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF2F5FC)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = habit.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = if (habit.isCompleted) Color.Gray else Blue
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = habit.description,
                    fontSize = 14.sp,
                    color = if (habit.isCompleted) Color.LightGray else Color.DarkGray
                )
            }

            Checkbox(
                checked = habit.isCompleted,
                onCheckedChange = { onToggleComplete(habit) },
                colors = CheckboxDefaults.colors(checkedColor = Blue)
            )

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                IconButton(
                    onClick = { onEdit(habit) },
                    modifier = Modifier.size(28.dp)
                ) {
                    Icon(
                        Icons.Default.Edit,
                        contentDescription = "Edit",
                        tint = Blue,
                        modifier = Modifier.size(20.dp)
                    )
                }
                IconButton(
                    onClick = { onDelete(habit) },
                    modifier = Modifier.size(28.dp)
                ) {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = Color.Red,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}
