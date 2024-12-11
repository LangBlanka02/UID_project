package com.example.gamificationapp.screens

import androidx.compose.foundation.layout.* // Import layout utilities
import androidx.compose.material3.* // Import material3 components
import androidx.compose.runtime.* // Import state handling utilities
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun LoginScreen(navController: NavHostController) {
    // State for username and password
    val username = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") } // For showing errors if needed

    // Predefined credentials
    val validCredentials = mapOf(
        "student" to "student123",
        "professor" to "professor123"
    )

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Title
            Text(
                text = "Login",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Username TextField
            TextField(
                value = username.value,
                onValueChange = { username.value = it },
                label = { Text("Username") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Password TextField
            TextField(
                value = password.value,
                onValueChange = { password.value = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Error Message
            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Login Button
            Button(
                onClick = {
                    val enteredUsername = username.value.lowercase()
                    val enteredPassword = password.value

                    when {
                        validCredentials[enteredUsername] == enteredPassword -> {
                            // Navigate to the respective dashboard
                            if (enteredUsername == "student") {
                                navController.navigate("student_dashboard")
                            } else if (enteredUsername == "professor") {
                                navController.navigate("professor_dashboard")
                            }
                        }
                        else -> {
                            errorMessage = "Invalid credentials. Please try again."
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Login")
            }
        }
    }
}
