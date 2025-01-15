package com.example.gamificationapp.screens.student

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gamificationapp.viewModel.PastQuizViewModel

@Composable
fun ReviewQuizScreen(viewModel: PastQuizViewModel) {
    val selectedQuiz = viewModel.selectedQuiz

    if (selectedQuiz == null) {
        // Center text if no quiz is selected
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "No quiz selected.",
                style = MaterialTheme.typography.bodyLarge
            )
        }
        return
    }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding) // Apply padding from Scaffold
                .padding(16.dp), // Additional padding for content
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            // Display the quiz title
            Text(
                text = selectedQuiz.quizTitle,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Iterate through questions and display answers
            selectedQuiz.questions.forEachIndexed { index, question ->
                val userAnswer = selectedQuiz.userAnswers[index]
                val isCorrect = userAnswer == question.correctAnswer

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .background(
                            if (isCorrect) MaterialTheme.colorScheme.secondaryContainer
                            else MaterialTheme.colorScheme.errorContainer
                        )
                        .padding(12.dp) // Inner padding for better spacing
                ) {
                    Text(
                        text = "Q${index + 1}: ${question.question}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "Your Answer: $userAnswer",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    if (!isCorrect) {
                        Text(
                            text = "Correct Answer: ${question.correctAnswer}",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = MaterialTheme.colorScheme.primary
                            )
                        )
//                        Text(
//                            text = "Explanation: ${question.explanation}",
//                            style = MaterialTheme.typography.bodySmall
//                        )
                    }
                }
            }
        }
    }
}
