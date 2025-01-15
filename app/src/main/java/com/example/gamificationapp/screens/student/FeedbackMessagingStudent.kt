package com.example.gamificationapp.screens.student
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gamificationapp.screens.professor.EngagementCard
import com.example.gamificationapp.screens.student.dto.Feedback

@Composable
fun FeedbackMessagingStudent() {
    val feedbackList = listOf(
        Feedback("Alice", "Great job on Module 1! Your understanding of the basics is commendable."),
        //Feedback("Bob", "Excellent performance on the quiz! Consider improving time management."),
        //Feedback("Charlie", "Good effort on Module 2. Focus on practice problems for better results."),
        //Feedback("Diana", "Amazing work on the challenge! Your problem-solving skills are impressive.")
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
            // Screen Title
            Text("Feedback and Messaging", style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(16.dp))

            // Feedback displayed in EngagementCard
            feedbackList.forEach { feedback ->
                EngagementCard(
                    title = "Student: ${feedback.studentName}",
                    details = listOf(
                        "Feedback: ${feedback.feedback}"
                    )
                )
                Spacer(modifier = Modifier.height(8.dp)) // Space between cards
            }
        }
    }
}