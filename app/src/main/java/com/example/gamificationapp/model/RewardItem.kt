package com.example.gamificationapp.model

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RewardItem(reward: Reward, totalPoints: Int) {
    Card(
        Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            if (reward.isEarned) MaterialTheme.colorScheme.primaryContainer
            else MaterialTheme.colorScheme.background
        )
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(reward.name, style = MaterialTheme.typography.titleLarge)
            Text(reward.description, style = MaterialTheme.typography.bodyMedium)
            Text("Points Needed: ${reward.milestone}", style = MaterialTheme.typography.bodySmall)

            // Progress bar
            val progress = (totalPoints.toFloat() / reward.milestone.toFloat()).coerceIn(0f, 1f)
            LinearProgressIndicator(
                progress = progress,
                modifier = Modifier.fillMaxWidth().height(8.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))

            if (progress < 1f) {
                Tooltip(
                    message = "You need ${reward.milestone - totalPoints} more points to unlock this reward",
                    modifier = Modifier.fillMaxWidth()
                )
            } else {
                Text("Reward Unlocked!", color = MaterialTheme.colorScheme.primary)
            }
        }
    }
}

@Composable
fun Tooltip(message: String, modifier: Modifier = Modifier) {
    Text(
        message,
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.secondary,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun RewardItemPreview() {
    RewardItem(
        reward = Reward(1, "Bonus Material", "Virtual", "Access to exclusive content", 100),
        totalPoints = 50
    )
}
