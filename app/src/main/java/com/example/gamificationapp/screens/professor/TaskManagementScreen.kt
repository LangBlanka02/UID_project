package com.example.gamificationapp.screens.professor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
@Composable
fun TaskManagementScreen(modifier: Modifier = Modifier) {
    var taskName by remember { mutableStateOf(TextFieldValue("")) }
    var taskDifficulty by remember { mutableStateOf("Beginner") }
    var rewardPoints by remember { mutableStateOf(0) }
    var tasks by remember { mutableStateOf(listOf<Task>()) }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Create Task", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        BasicTextField(
            value = taskName,
            onValueChange = { taskName = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .border(1.dp, Color.Gray),
            singleLine = true
        )
        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = Color.Red, style = MaterialTheme.typography.bodySmall)
        }
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Select Difficulty: $taskDifficulty")
        Row(modifier = Modifier.padding(vertical = 8.dp)) {
            listOf("Beginner", "Intermediate", "Advanced").forEach { difficulty ->
                Button(
                    onClick = { taskDifficulty = difficulty },
                    modifier = Modifier.padding(horizontal = 4.dp)
                ) {
                    Text(text = difficulty)
                }
            }
        }

        Text(text = "Reward Points: $rewardPoints")
        Slider(
            value = rewardPoints.toFloat(),
            onValueChange = { rewardPoints = it.toInt() },
            valueRange = 0f..100f,
            steps = 9
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (taskName.text.isBlank()) {
                errorMessage = "Task name cannot be empty."
            } else {
                tasks = tasks + Task(taskName.text, taskDifficulty, rewardPoints)
                taskName = TextFieldValue("")
                rewardPoints = 0
                errorMessage = ""
            }
        }) {
            Text("Add Task")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Tasks", style = MaterialTheme.typography.titleMedium)
        LazyColumn {
            items(tasks.size) { index ->
                TaskItem(
                    task = tasks[index],
                    onDelete = {
                        tasks = tasks.toMutableList().apply { removeAt(index) }
                    }
                )
            }
        }
    }
}

@Composable
fun TaskItem(task: Task, onDelete: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = "Name: ${task.name}", style = MaterialTheme.typography.bodyLarge)
                Text(
                    text = "Difficulty: ${task.difficulty}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Reward Points: ${task.points}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Button(onClick = onDelete) {
                Text("Delete")
            }
        }
    }
}

data class Task(val name: String, val difficulty: String, val points: Int)
