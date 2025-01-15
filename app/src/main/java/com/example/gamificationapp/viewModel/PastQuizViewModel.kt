package com.example.gamificationapp.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.gamificationapp.models.CompletedQuiz
import com.example.gamificationapp.models.FlashQuizQuestion

class PastQuizViewModel : ViewModel() {
    private val allQuizzes = listOf(
        CompletedQuiz(
            quizTitle = "General Knowledge Quiz",
            questions = listOf(
                FlashQuizQuestion(
                    question = "What is the capital of France?",
                    options = listOf("Paris", "London", "Berlin", "Madrid"),
                    correctAnswer = "Paris",
                    explanation = "Paris is the capital and most populous city of France."
                ),
                FlashQuizQuestion(
                    question = "What is 5 + 7?",
                    options = listOf("10", "11", "12", "13"),
                    correctAnswer = "12",
                    explanation = "The sum of 5 and 7 is 12."
                )
            ),
            userAnswers = mapOf(0 to "London", 1 to "12") // First answer is incorrect
        )
    )

    var selectedQuiz by mutableStateOf<CompletedQuiz?>(null)

    fun getCompletedQuizzes(): List<CompletedQuiz> = allQuizzes

    fun loadQuiz(quizId: String) {
        selectedQuiz = allQuizzes.find { it.quizTitle == quizId }
    }
    fun selectQuiz(quizTitle: String) {
        selectedQuiz = allQuizzes.find { it.quizTitle == quizTitle }
    }
}