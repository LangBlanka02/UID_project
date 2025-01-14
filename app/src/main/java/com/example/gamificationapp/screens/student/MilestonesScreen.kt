package com.example.gamificationapp.screens.student

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Milestone(
    val title: String,
    val description: String,
    val unlocked: Boolean
)

@Composable
fun MilestonesScreen() {
    val mockMilestones = listOf(
        Milestone(
            title = "First Steps",
            description = "Complete the first three modules to unlock this milestone.",
            unlocked = true
        ),
        Milestone(
            title = "Quiz Master",
            description = "Score above 80% in all quizzes to unlock this milestone.",
            unlocked = false
        ),
        Milestone(
            title = "Mid-term Pro",
            description = "Complete all mid-term assignments to unlock this milestone.",
            unlocked = false
        ),
        Milestone(
            title = "Final Stretch",
            description = "Complete the final exam with a score above 90%.",
            unlocked = false
        )
    )

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                "Course Milestones",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "Track your progress and unlock achievements as you complete key tasks in the course.",
                style = MaterialTheme.typography.bodyMedium,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            LazyColumn {
                items(mockMilestones) { milestone ->
                    MilestoneCard(milestone)
                }
            }
        }
    }
}

@Composable
fun MilestoneCard(milestone: Milestone) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = if (milestone.unlocked) Icons.Filled.Star else Icons.Outlined.Lock,
                contentDescription = if (milestone.unlocked) "Unlocked" else "Locked",
                modifier = Modifier.size(32.dp),
                tint = if (milestone.unlocked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    milestone.title,
                    style = MaterialTheme.typography.bodyLarge,
                    color = if (milestone.unlocked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    milestone.description,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}
