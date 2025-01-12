package com.example.gamificationapp.models

data class ActivityLog(
    val id: Int = 0,
    val activityType: String,
    val pointsEarned: Int
)