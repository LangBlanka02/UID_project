package com.example.gamificationapp.screens.student

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

// Data class for Leaderboard Entry (all in one file now)
data class LeaderboardEntry(
    val studentId: String,
    val studentName: String,
    val rank: Int,
    val score: Int
)

@Composable
fun CourseLeaderboardScreen(navController: NavHostController, loggedInUserName: String) {
    // Hardcoded data function (all in one file now)
    fun getHardcodedLeaderboardData(loggedInUserName: String): List<LeaderboardEntry> {
        val leaderboardData = mutableListOf(
            LeaderboardEntry("1", "Alice", 1, 1500),
            LeaderboardEntry("2", "Bob", 2, 1400),
            LeaderboardEntry("3", "Charlie", 3, 1350),
            LeaderboardEntry("4", "David", 4, 1200),
            LeaderboardEntry("5", "Eve", 5, 1150)
            // ... add more entries as needed
        )

        // Check if the logged-in user is already in the list
        val userEntry = leaderboardData.find { it.studentName == loggedInUserName }

        if (userEntry == null) {
            // Add the logged-in user with a random score
            val userScore = (1000..1600).random()
            leaderboardData.add(LeaderboardEntry("user", loggedInUserName, 0, userScore))
        }

        // Sort the list by score (descending) and update ranks
        leaderboardData.sortByDescending { it.score }
        for (i in leaderboardData.indices) {
            leaderboardData[i] = leaderboardData[i].copy(rank = i + 1)
        }

        return leaderboardData
    }

    // Get hardcoded data, including the logged-in user
    val leaderboardEntries = remember { mutableStateOf(getHardcodedLeaderboardData(loggedInUserName)) }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Course Leaderboard", style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(16.dp))

            // Display the leaderboard using LazyColumn
            LazyColumn(contentPadding = PaddingValues(16.dp)) {
                items(leaderboardEntries.value) { entry ->
                    LeaderboardItem(entry, loggedInUserName)
                }
            }
        }
    }
}

@Composable
fun LeaderboardItem(entry: LeaderboardEntry, loggedInUserName: String) {
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
                Text(
                    text = entry.studentName,
                    style = MaterialTheme.typography.bodyLarge,
                    // Highlight logged-in user's name
                    color = if (entry.studentName == loggedInUserName) MaterialTheme.colorScheme.primary else Color.Unspecified
                )
                Text(text = "Score: ${entry.score}", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}