package com.example.gamificationapp.screens.professor

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun ProfessorDashboardScreen(navController: NavHostController) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header
            Text("Professor Dashboard", style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(16.dp))

            // Buttons
            Button(
                onClick = { navController.navigate("engagement_reports") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Engagement Reports")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { navController.navigate("task_management") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Task and Activity Management")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { navController.navigate("course_leaderboard_professor") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Course Leaderboard Insights")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { navController.navigate("low_engagement_notifications") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Notifications for Low Engagement")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { navController.navigate("gamification_settings") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Gamification Settings")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { navController.navigate("team_challenge_management") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Team Challenge Management")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { navController.navigate("feedback_messaging") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Feedback and Messaging")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { navController.navigate("analytics_dashboard") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Analytics Dashboard")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { navController.navigate("milestone_configuration") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Milestone Configuration")
            }
        }
    }
}
