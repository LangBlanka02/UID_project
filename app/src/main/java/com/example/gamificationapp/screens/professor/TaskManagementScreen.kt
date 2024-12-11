package com.example.gamificationapp.screens.professor

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun TaskManagementScreen() {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text("Task and Activity Management", style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(16.dp))

            // Create Task Button
            Button(onClick = { /* Add task creation logic */ }, modifier = Modifier.fillMaxWidth()) {
                Text("Create New Task")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Example Tasks
            Text("Task 1: Quiz on Module 1 | Points: 10 | Deadline: Tomorrow")
        }
    }
}
