package com.example.gamificationapp.screens.student

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gamificationapp.models.FlashQuizQuestion
import com.example.gamificationapp.viewModel.FlashQuizViewModel
import com.example.gamificationapp.models.RewardViewModel

@Composable
fun FlashQuizScreen(
    flashQuizViewModel: FlashQuizViewModel = viewModel(),
    rewardViewModel: RewardViewModel = viewModel()
) {
    val remainingTime by remember { derivedStateOf { flashQuizViewModel.remainingTime } }
    val currentQuestion by remember { derivedStateOf { flashQuizViewModel.currentQuestion } }
    val isQuizComplete by remember { derivedStateOf { flashQuizViewModel.isQuizComplete } }
    val score by remember { derivedStateOf { flashQuizViewModel.score } }

    // Add points to the total when the quiz is complete
    LaunchedEffect(isQuizComplete) {
        if (isQuizComplete) {
            rewardViewModel.addActivityPoints("Flash Quiz Completed", score)
        }
    }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (isQuizComplete) {
                QuizCompleteScreen(score = score)
            } else {
                FlashQuizContent(
                    question = currentQuestion,
                    remainingTime = remainingTime,
                    onAnswerSelected = { flashQuizViewModel.submitAnswer(it) }
                )
            }
        }
    }
}

@Composable
fun FlashQuizContent(
    question: FlashQuizQuestion,
    remainingTime: Int,
    onAnswerSelected: (String) -> Unit
) {
    Text(
        text = "Time Remaining: $remainingTime seconds",
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier.padding(8.dp)
    )

    Spacer(modifier = Modifier.height(16.dp))

    Text(
        text = question.question,
        style = MaterialTheme.typography.headlineMedium,
        modifier = Modifier.padding(8.dp)
    )

    Spacer(modifier = Modifier.height(16.dp))

    question.options.forEach { option ->
        Button(
            onClick = { onAnswerSelected(option) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            Text(option)
        }
    }
}

@Composable
fun QuizCompleteScreen(score: Int) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Quiz Complete!",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Your Score: $score",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}
