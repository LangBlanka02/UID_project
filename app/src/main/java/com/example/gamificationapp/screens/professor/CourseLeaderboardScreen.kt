package com.example.gamificationapp.screens.professor

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

// Data class for Leaderboard Entry (same as for the student)
data class LeaderboardEntry(
    val studentId: String,
    val studentName: String,
    val rank: Int,
    val score: Int
)

@Composable
fun CourseLeaderboardScreenProfessor(navController: NavHostController) {
    // Hardcoded data function (modified for professor, no user insertion)
    fun getHardcodedLeaderboardData(): List<LeaderboardEntry> {
        return listOf(
            LeaderboardEntry("1", "Alice", 1, 1500),
            LeaderboardEntry("2", "Bob", 2, 1400),
            LeaderboardEntry("3", "Charlie", 3, 1350),
            LeaderboardEntry("4", "David", 4, 1200),
            LeaderboardEntry("5", "Eve", 5, 1150)
            // ... add more entries as needed
        ).sortedBy { it.rank } // Ensure it's sorted by rank
    }

    // Get hardcoded data
    val leaderboardEntries = remember { mutableStateOf(getHardcodedLeaderboardData()) }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Course Leaderboard Insights", style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(16.dp))

            // Display the leaderboard using LazyColumn
            LazyColumn(contentPadding = PaddingValues(16.dp)) {
                items(leaderboardEntries.value) { entry ->
                    LeaderboardItem(entry)
                }
            }
        }
    }
}

@Composable
fun LeaderboardItem(entry: LeaderboardEntry) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "${entry.rank}.", modifier = Modifier.width(40.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = entry.studentName, style = MaterialTheme.typography.bodyLarge)
                Text(text = "Score: ${entry.score}", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}