package com.example.gamificationapp.models

data class Reward(
    val id: Int,
    val name: String,
    val type: String, // "Tangible" or "Virtual"
    val description: String,
    val milestone: Int, // Points required
    var isEarned: Boolean = false)
