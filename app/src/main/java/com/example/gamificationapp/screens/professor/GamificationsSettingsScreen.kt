package com.example.gamificationapp.screens.professor

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class GamificationCriteria(
    val activityName: String,
    val pointsAwarded: Int,
    val achievementUnlocked: String
)

@Composable
fun GamificationSettingsScreen() {
    val mockData = listOf(
        GamificationCriteria("Quiz Completion", 10, "Quiz Master"),
        GamificationCriteria("Discussion Participation", 5, "Engaged Learner"),
        GamificationCriteria("Assignment Submission", 15, "Assignment Ace"),
        GamificationCriteria("Perfect Attendance", 20, "Attendance Star"),
        GamificationCriteria("Top Score in Exam", 50, "Exam Champion")
    )

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text("Gamification Settings", style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "Set up the gamification criteria for your course. Define which activities will award points and the corresponding achievements.",
                style = MaterialTheme.typography.bodyMedium,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            LazyColumn {
                items(mockData) { criteria ->
                    GamificationCriteriaCard(criteria)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { /* Add Save Action */ },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Save Settings")
            }
        }
    }
}

@Composable
fun GamificationCriteriaCard(criteria: GamificationCriteria) {
    val pointsInput = remember { mutableStateOf(criteria.pointsAwarded.toString()) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text("Activity: ${criteria.activityName}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Achievement: ${criteria.achievementUnlocked}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Points: ", style = MaterialTheme.typography.bodyMedium)
                TextField(
                    value = pointsInput.value,
                    onValueChange = { pointsInput.value = it },
                    modifier = Modifier.width(80.dp),
                    singleLine = true
                )
            }
        }
    }
}
