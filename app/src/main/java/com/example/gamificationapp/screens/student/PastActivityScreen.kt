package com.example.gamificationapp.screens.student

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
fun PastActivityScreen(navController: NavHostController) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Review Past Activity", style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(16.dp))

            // Example past activities
            Text("Past Quizzes:")
            Button(
                onClick = { navController.navigate("past_quiz_page") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("View Quiz 1")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Past Assignments:")
            Button(
                onClick = { navController.navigate("past_assignment_page") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("View Assignment 1")
            }
        }
    }
}
