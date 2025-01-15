package com.example.gamificationapp.models


data class FlashQuizQuestion(
    val question: String,
    val options: List<String>,
    val correctAnswer: String
) {

}