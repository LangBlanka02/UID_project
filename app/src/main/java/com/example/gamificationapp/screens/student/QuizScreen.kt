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

@Composable
fun QuizScreen() {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text("Quiz Page", style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(16.dp))

            // Example Quiz Question
            Text("Question: What is the capital of France?", style = MaterialTheme.typography.bodyLarge)

            Spacer(modifier = Modifier.height(16.dp))

            // Options
            Button(onClick = { /* Handle Answer */ }, modifier = Modifier.fillMaxWidth()) {
                Text("Option 1: Paris")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { /* Handle Answer */ }, modifier = Modifier.fillMaxWidth()) {
                Text("Option 2: Madrid")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { /* Handle Answer */ }, modifier = Modifier.fillMaxWidth()) {
                Text("Option 3: Rome")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Submit Button
            Button(onClick = { /* Navigate to Results */ }, modifier = Modifier.fillMaxWidth()) {
                Text("Submit Quiz")
            }
        }
    }
}
