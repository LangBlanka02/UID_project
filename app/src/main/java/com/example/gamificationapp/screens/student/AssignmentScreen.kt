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
fun AssignmentScreen() {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text("Assignment Page", style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(16.dp))

            // Assignment Details in EngagementCard
            EngagementCard(
                title = "Assignment Details",
                details = listOf(
                    "Write an essay about climate change.",
                    "Submission Status: Pending"
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Upload Button
            Button(
                onClick = { /* Handle File Upload */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Upload Assignment")
            }
        }
    }
}
