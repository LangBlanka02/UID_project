package com.example.gamificationapp.model

data class Badge(
    val id: Int,
    val icon: Int, // Resource ID for the badge icon
    val name: String,
    val description: String,
    val isEarned: Boolean = false
)
