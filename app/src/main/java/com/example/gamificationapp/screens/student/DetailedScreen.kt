package com.example.gamificationapp.screens.student

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

class DetailedScreen {
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

}