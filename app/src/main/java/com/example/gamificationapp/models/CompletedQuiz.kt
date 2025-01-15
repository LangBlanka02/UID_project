package com.example.gamificationapp.models

data class CompletedQuiz(
    val quizTitle: String,
    val questions: List<FlashQuizQuestion>,
    val userAnswers: Map<Int, String> // Maps question index to user's answer
)