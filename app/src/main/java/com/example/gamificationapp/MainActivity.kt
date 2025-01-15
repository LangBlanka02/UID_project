package com.example.gamificationapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.gamificationapp.models.RewardViewModel
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
import com.example.gamificationapp.screens.professor.CourseLeaderboardScreenProfessor
import com.example.gamificationapp.screens.professor.EngagementReportsScreen
import com.example.gamificationapp.screens.professor.FeedbackMessagingScreen
import com.example.gamificationapp.screens.professor.GamificationSettingsScreen
import com.example.gamificationapp.screens.professor.LowEngagementNotificationsScreen
import com.example.gamificationapp.screens.professor.MilestoneConfigurationScreen
import com.example.gamificationapp.screens.professor.TaskManagementScreen
import com.example.gamificationapp.screens.professor.TeamChallengeManagementScreen
import com.example.gamificationapp.screens.student.ChallengeDetailScreen
import com.example.gamificationapp.screens.student.TimedQuizScreen
import com.example.gamificationapp.ui.theme.GamificationAppTheme
import com.example.gamificationapp.viewModel.FlashQuizViewModel

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
    val rewardViewModel: RewardViewModel = androidx.lifecycle.viewmodel.compose.viewModel()

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
            composable(
                "student_dashboard/{userName}",
                arguments = listOf(navArgument("userName") { type = NavType.StringType })
            ) { backStackEntry ->
                val userName = backStackEntry.arguments?.getString("userName") ?: ""
                StudentDashboardScreen(navController, userName)
            }

            // Professor Dashboard
            composable("professor_dashboard") { ProfessorDashboardScreen(navController) }

            // Expanded Options for Student Dashboard
            composable(
                "course_leaderboard/{userName}",
                arguments = listOf(navArgument("userName") { type = NavType.StringType })
            ) { backStackEntry ->
                val userName = backStackEntry.arguments?.getString("userName") ?: ""
                CourseLeaderboardScreen(navController, userName)
            }
            composable("feedback_student") { com.example.gamificationapp.screens.student.FeedbackMessagingScreen()}
            composable("timed_quiz") {TimedQuizScreen(navController,rewardViewModel = rewardViewModel)}
            composable("task_overview") { TaskOverviewScreen(navController) }
            composable("earned_badges") { EarnedBadgesScreen(viewModel = rewardViewModel) }
            composable("reward_catalog") { RewardCatalogScreen(viewModel = rewardViewModel) }
            composable("milestones") { MilestonesScreen() }
            composable("team_challenges") { TeamChallengesScreen(navController) }
            composable(
                "challenge_detail/{challengeName}/{challengeDescription}",
                arguments = listOf(
                    navArgument("challengeName") { type = NavType.StringType },
                    navArgument("challengeDescription") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val challengeName = backStackEntry.arguments?.getString("challengeName") ?: ""
                val challengeDescription = backStackEntry.arguments?.getString("challengeDescription") ?: ""
                ChallengeDetailScreen(challengeName, challengeDescription, navController)
            }
            composable("past_activity") { PastActivityScreen(navController) }

            // Additional sub-navigation for tasks and past activities
            composable("flash_quiz_page") { FlashQuizScreen(rewardViewModel = rewardViewModel) }
            composable("assignment_page") { AssignmentScreen() }
            composable("past_quiz_page") { PastQuizScreen() }
            composable("past_assignment_page") { PastAssignmentScreen() }

            // Expanded Options for Professor Dashboard
            composable("engagement_reports") { EngagementReportsScreen() }
            composable("task_management") { TaskManagementScreen() }
            composable("course_leaderboard_professor") { CourseLeaderboardScreenProfessor(navController) }
            composable("low_engagement_notifications") { LowEngagementNotificationsScreen() }
            composable("gamification_settings") { GamificationSettingsScreen() }
            composable("team_challenge_management") { TeamChallengeManagementScreen() }
            composable("feedback_messaging") { FeedbackMessagingScreen() }
            composable("analytics_dashboard") { AnalyticsDashboardScreen() }
            composable("milestone_configuration") { MilestoneConfigurationScreen() }
        }
    }
}



