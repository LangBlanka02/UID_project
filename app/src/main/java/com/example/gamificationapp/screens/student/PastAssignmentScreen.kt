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
import com.example.gamificationapp.screens.professor.EngagementCard

@Composable
fun PastAssignmentScreen() {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text("Past Assignment Page", style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(16.dp))

            // Example Past Assignment in EngagementCard
            EngagementCard(
                title = "Assignment 1: Essay on Climate Change",
                details = listOf(
                    "Grade: A"
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // View Assignment Button
            Button(
                onClick = { /* Navigate to Assignment Details */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Download Submitted Assignment")
            }
        }
    }
}
