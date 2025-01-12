package com.example.gamificationapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gamificationapp.screens.student.AssignmentScreen
import com.example.gamificationapp.screens.student.CourseLeaderboardScreen
import com.example.gamificationapp.screens.student.EarnedBadgesScreen
import com.example.gamificationapp.screens.LoginScreen
import com.example.gamificationapp.screens.student.MilestonesScreen
import com.example.gamificationapp.screens.student.PastActivityScreen
import com.example.gamificationapp.screens.student.PastAssignmentScreen
import com.example.gamificationapp.screens.student.PastQuizScreen
import com.example.gamificationapp.screens.professor.ProfessorDashboardScreen
import com.example.gamificationapp.screens.student.FlashQuizScreen
import com.example.gamificationapp.screens.student.RewardCatalogScreen
import com.example.gamificationapp.screens.student.StudentDashboardScreen
import com.example.gamificationapp.screens.student.TaskOverviewScreen
import com.example.gamificationapp.screens.student.TeamChallengesScreen
import com.example.gamificationapp.screens.professor.AnalyticsDashboardScreen
import com.example.gamificationapp.screens.professor.EngagementReportsScreen
import com.example.gamificationapp.screens.professor.FeedbackMessagingScreen
import com.example.gamificationapp.screens.professor.GamificationSettingsScreen
import com.example.gamificationapp.screens.professor.LowEngagementNotificationsScreen
import com.example.gamificationapp.screens.professor.MilestoneConfigurationScreen
import com.example.gamificationapp.screens.professor.TaskManagementScreen
import com.example.gamificationapp.screens.professor.TeamChallengeManagementScreen
import com.example.gamificationapp.ui.theme.GamificationAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GamificationAppTheme {
                GamificationApp()
            }
        }
    }
}

@Composable
fun GamificationApp() {
    val navController = rememberNavController()
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        // Apply the padding parameter to the NavHost
        NavHost(
            navController = navController,
            startDestination = "login",
            modifier = Modifier.padding(innerPadding)
        ) {
            // Login Screen
            composable("login") { LoginScreen(navController) }

            // Student Dashboard
            composable("student_dashboard") { StudentDashboardScreen(navController) }

            // Professor Dashboard
            composable("professor_dashboard") { ProfessorDashboardScreen(navController) }

            // Expanded Options for Student Dashboard
            composable("course_leaderboard") { CourseLeaderboardScreen() }
            composable("task_overview") { TaskOverviewScreen(navController) }
            composable("earned_badges") { EarnedBadgesScreen() }
            composable("reward_catalog") { RewardCatalogScreen() }
            composable("milestones") { MilestonesScreen() }
            composable("team_challenges") { TeamChallengesScreen() }
            composable("past_activity") { PastActivityScreen(navController) }

            // Additional sub-navigation for tasks and past activities
            composable("flash_quiz_page") { FlashQuizScreen() }
            composable("assignment_page") { AssignmentScreen() }
            composable("past_quiz_page") { PastQuizScreen() }
            composable("past_assignment_page") { PastAssignmentScreen() }

            // Expanded Options for Professor Dashboard
            composable("engagement_reports") { EngagementReportsScreen() }
            composable("task_management") { TaskManagementScreen() }
            composable("low_engagement_notifications") { LowEngagementNotificationsScreen() }
            composable("gamification_settings") { GamificationSettingsScreen() }
            composable("team_challenge_management") { TeamChallengeManagementScreen() }
            composable("feedback_messaging") { FeedbackMessagingScreen() }
            composable("analytics_dashboard") { AnalyticsDashboardScreen() }
            composable("milestone_configuration") { MilestoneConfigurationScreen() }
        }
    }
}




