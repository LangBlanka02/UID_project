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
fun StudentDashboardScreen(navController: NavHostController) {
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
            Text("Student Dashboard", style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(16.dp))

            // Buttons
            Button(
                onClick = { navController.navigate("course_leaderboard") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("View Course Leaderboard")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { navController.navigate("task_overview") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Task Overview")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { navController.navigate("earned_badges") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Earned Badges")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { navController.navigate("reward_catalog") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Reward Catalog")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { navController.navigate("milestones") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Milestones")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { navController.navigate("team_challenges") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Team Challenges")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { navController.navigate("past_activity") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Review Past Activity")
            }
        }
    }
}
