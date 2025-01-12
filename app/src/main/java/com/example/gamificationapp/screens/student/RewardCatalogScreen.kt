package com.example.gamificationapp.screens.student

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gamificationapp.model.Reward
import com.example.gamificationapp.model.RewardViewModel
import com.example.gamificationapp.model.RewardItem

@Composable
fun RewardCatalogScreen(viewModel: RewardViewModel) {
    val rewards by viewModel.rewards.observeAsState(initial = emptyList<Reward>())
    val totalPoints by viewModel.totalPoints.observeAsState(initial = 0)

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Total Points: $totalPoints", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(rewards) { reward ->
                RewardItem(
                    reward = reward,
                    totalPoints = totalPoints
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { viewModel.addActivityPoints("Quiz Completed", 50) }) {
            Text("Complete Activity (+ Points)")
        }
    }
}
