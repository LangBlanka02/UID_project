package com.example.gamificationapp.screens

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
fun TaskOverviewScreen(navController: NavHostController) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Task Overview", style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(16.dp))

            // Example tasks
            Text("Pending Quizzes:")
            Button(
                onClick = { navController.navigate("quiz_page") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Start Quiz 1")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Pending Assignments:")
            Button(
                onClick = { navController.navigate("assignment_page") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Upload Assignment 1")
            }
        }
    }
}
