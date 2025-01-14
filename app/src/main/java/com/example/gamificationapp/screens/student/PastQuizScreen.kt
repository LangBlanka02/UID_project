package com.example.gamificationapp.screens.student

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class QuizReview(
    val question: String,
    val incorrectAnswer: String,
    val correctAnswer: String,
    val explanation: String
)

@Composable
fun PastQuizScreen() {
    val mockData = listOf(
        QuizReview(
            question = "What is the capital of France?",
            incorrectAnswer = "Berlin",
            correctAnswer = "Paris",
            explanation = "Paris is the capital of France, known for its art, culture, and history."
        ),
        QuizReview(
            question = "Which element has the atomic number 1?",
            incorrectAnswer = "Helium",
            correctAnswer = "Hydrogen",
            explanation = "Hydrogen is the lightest element and has the atomic number 1."
        ),
        QuizReview(
            question = "Who wrote 'Romeo and Juliet'?",
            incorrectAnswer = "Charles Dickens",
            correctAnswer = "William Shakespeare",
            explanation = "William Shakespeare is known for his plays, including 'Romeo and Juliet'."
        )
    )

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                "Review Incorrect Answers",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "Learn from your mistakes by reviewing incorrect answers and understanding why the correct answers are right.",
                style = MaterialTheme.typography.bodyMedium,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            LazyColumn {
                items(mockData) { quizReview ->
                    QuizReviewCard(quizReview)
                }
            }
        }
    }
}

@Composable
fun QuizReviewCard(quizReview: QuizReview) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text("Question: ${quizReview.question}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Your Answer: ${quizReview.incorrectAnswer}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.error
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Correct Answer: ${quizReview.correctAnswer}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Explanation: ${quizReview.explanation}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
