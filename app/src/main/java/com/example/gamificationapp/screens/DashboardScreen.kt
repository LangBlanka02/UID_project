package com.example.gamificationapp.screens
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController


@Composable
fun DashboardScreen(navController: NavHostController) {
    Scaffold { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Button(onClick = { navController.navigate("leaderboard") }) {
                Text("Go to Leaderboard")
            }
            Button(onClick = { navController.navigate("rewards") }) {
                Text("Go to Rewards Catalog")
            }
        }
    }
}
