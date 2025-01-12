package com.example.gamificationapp.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import com.example.gamificationapp.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class RewardViewModel : ViewModel() {
    // Rewards Logic
    private val _rewards = MutableLiveData<List<Reward>>()
    val rewards: LiveData<List<Reward>> get() = _rewards

    private val _totalPoints = MutableLiveData<Int>(0)
    val totalPoints: LiveData<Int> get() = _totalPoints

    // Badges Logic
    private val _allBadges = MutableLiveData<List<Badge>>()
    val allBadges: LiveData<List<Badge>> get() = _allBadges

    private val _earnedBadges = MutableStateFlow<List<Badge>>(emptyList())
    val earnedBadges: StateFlow<List<Badge>> get() = _earnedBadges

    init {
        _rewards.value = listOf(
            Reward(1, "Bonus Material", "Virtual", "Access to exclusive content", 150),
            Reward(2, "Certificate", "Tangible", "Completion certificate", 500)
        )

        _allBadges.value = listOf(
            Badge(1, R.drawable.quizmaster, "Beginner", "Complete your first task"),
            Badge(2, R.drawable.quizmaster, "Achiever", "Complete 5 tasks"),
            Badge(3, R.drawable.quizmaster, "Master", "Complete 10 tasks")
        )
        _earnedBadges.value = emptyList()
    }

    fun addActivityPoints(activity: String, points: Int) {
        _totalPoints.value = (_totalPoints.value ?: 0) + points
        checkForBadges()
    }

    // Check if any badges are earned
    fun checkForBadges() {
        val totalPoints = _totalPoints.value ?: 0
        val earned = _earnedBadges.value?.toMutableList() ?: mutableListOf()

        _allBadges.value?.forEach { badge ->
            if (!earned.contains(badge)) {
                when (badge.name) {
                    "Beginner" -> if (totalPoints >= 10) earned.add(badge)
                    "Achiever" -> if (totalPoints >= 50) earned.add(badge)
                    "Master" -> if (totalPoints >= 100) earned.add(badge)
                }
            }
        }

        _earnedBadges.value = earned
    }

    fun earnBadge(badgeId: Int) {
        val badge = _allBadges.value?.find { it.id == badgeId }
        if (badge != null && !_earnedBadges.value.contains(badge)) {
            _earnedBadges.update { it + badge.copy(isEarned = true) }
        }
    }
}
