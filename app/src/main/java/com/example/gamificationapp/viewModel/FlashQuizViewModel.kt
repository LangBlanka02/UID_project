package com.example.gamificationapp.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamificationapp.models.FlashQuizQuestion
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FlashQuizViewModel : ViewModel() {
    private val _questions = listOf(
        FlashQuizQuestion(
            question = "What is the capital of France?",
            options = listOf("Paris", "London", "Berlin", "Madrid"),
            correctAnswer = "Paris"
        ),
        FlashQuizQuestion(
            question = "What is 5 + 7?",
            options = listOf("10", "11", "12", "13"),
            correctAnswer = "12"
        ),
        FlashQuizQuestion(
            question = "Who wrote '1984'?",
            options = listOf("Orwell", "Huxley", "Kafka", "Hemingway"),
            correctAnswer = "Orwell"
        )
    )

    var currentQuestionIndex by mutableStateOf(0)
    var remainingTime by mutableStateOf(30)
    var score by mutableStateOf(0)
    var isQuizComplete by mutableStateOf(false)

    val currentQuestion: FlashQuizQuestion
        get() = _questions.getOrNull(currentQuestionIndex) ?: FlashQuizQuestion(
            question = "Quiz Complete",
            options = emptyList(),
            correctAnswer = ""
        )

    init {
        startTimer()
    }

    private fun startTimer() {
        viewModelScope.launch {
            while (remainingTime > 0 && !isQuizComplete) {
                delay(1000)
                remainingTime--
            }
            if (remainingTime == 0) {
                isQuizComplete = true
            }
        }
    }

    fun submitAnswer(selectedAnswer: String) {
        if (selectedAnswer == currentQuestion.correctAnswer) {
            score += 10
        }
        if (currentQuestionIndex + 1 < _questions.size) {
            currentQuestionIndex++
        } else {
            isQuizComplete = true
        }
    }
}
