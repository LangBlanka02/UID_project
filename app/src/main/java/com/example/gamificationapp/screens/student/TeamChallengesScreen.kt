package com.example.gamificationapp.screens.student

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun TeamChallengesScreen(navController: NavController) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Team Challenges", style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(16.dp))

            TeamChallengesContent(
                modifier = Modifier.fillMaxSize(),
                navController = navController
            )
        }
    }
}

@Composable
fun TeamChallengesContent(modifier: Modifier, navController: NavController) {
    var searchText by remember { mutableStateOf(TextFieldValue("")) }

    // Mock data for team challenges
    val challenges = listOf(
        TeamChallenge("Project Collaboration", "Complete a group project on AI topics."),
        TeamChallenge("Group Quiz", "Participate in a quiz with your team."),
        TeamChallenge("Hackathon", "Develop an app prototype with your group.")
    )

    Column(
        modifier = modifier.padding(16.dp)
    ) {
        // Search Bar
        BasicTextField(
            value = searchText,
            onValueChange = { searchText = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .height(48.dp),
            decorationBox = { innerTextField ->
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium,
                    tonalElevation = 4.dp
                ) {
                    Box(
                        Modifier
                            .padding(horizontal = 16.dp, vertical = 12.dp)
                            .fillMaxWidth()
                    ) {
                        if (searchText.text.isEmpty()) {
                            Text("Search challenges...")
                        }
                        innerTextField()
                    }
                }
            }
        )

        // Team Challenges List
        LazyColumn {
            items(challenges.filter {
                it.name.contains(searchText.text, ignoreCase = true)
            }) { challenge ->
                TeamChallengeCard(challenge, navController)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun TeamChallengeCard(challenge: TeamChallenge, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        onClick = {
            navController.navigate(
                "challenge_detail/${challenge.name}/${challenge.description}"
            )
        }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = challenge.name, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = challenge.description, style = MaterialTheme.typography.bodyMedium)
        }
    }
}


@Composable
fun ChallengeDetailScreen(
    challengeName: String,
    challengeDescription: String,
    navController: NavController
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = challengeName, style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = challengeDescription, style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(32.dp))

            // Join Button
            var showSnackbar by remember { mutableStateOf(false) }
            Button(
                onClick = { showSnackbar = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Join Challenge")
            }

            // Snackbar to show registration success
            if (showSnackbar) {
                Snackbar(
                    action = {
                        TextButton(onClick = { showSnackbar = false }) {
                            Text("Dismiss")
                        }
                    },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text("Registration successfully")
                }
            }
        }
    }
}

data class TeamChallenge(val name: String, val description: String)
