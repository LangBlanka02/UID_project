package com.example.gamificationapp.screens.student

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds
import kotlin.time.Duration.Companion.minutes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

// Data Structures
data class QuizQuestion(
    val id: Int,
    val text: String,
    val options: List<String>,
    val correctAnswer: Int,
    val questionType: QuestionType = QuestionType.MULTIPLE_CHOICE
)

enum class QuestionType {
    MULTIPLE_CHOICE,
    TRUE_FALSE
}

@Composable
fun TimedQuizScreen(navController: NavHostController) {
    val quizQuestions = remember { getHardcodedQuizData().shuffled() } // Shuffle questions
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var selectedAnswerIndex by remember { mutableStateOf<Int?>(null) }
    var score by remember { mutableStateOf(0) }
    var timeLeft by remember { mutableStateOf(10.minutes) } // 10 minutes
    var isQuizOver by remember { mutableStateOf(false) }

    // Timer coroutine
    LaunchedEffect(key1 = timeLeft) {
        if (timeLeft > 0.seconds && !isQuizOver) {
            delay(1.seconds)
            timeLeft = timeLeft - 1.seconds
        } else {
            isQuizOver = true
        }
    }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (isQuizOver) {
                // Quiz Over Screen
                QuizOverScreen(score = score, totalQuestions = quizQuestions.size)
            } else {
                // Quiz in Progress
                Text(
                    "Time Left: ${timeLeft.inWholeSeconds}s",
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Question Display
                Text(
                    text = quizQuestions[currentQuestionIndex].text,
                    style = MaterialTheme.typography.headlineSmall
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Answer Options
                quizQuestions[currentQuestionIndex].options.forEachIndexed { index, option ->
                    AnswerOption(
                        optionText = option,
                        isSelected = selectedAnswerIndex == index,
                        isCorrect = if (selectedAnswerIndex != null) {
                            index == quizQuestions[currentQuestionIndex].correctAnswer
                        } else {
                            null
                        },
                        onOptionSelected = {
                            if (selectedAnswerIndex == null) {
                                selectedAnswerIndex = index
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Next Question Button
                Button(
                    onClick = {
                        // Check the answer and update score
                        if (selectedAnswerIndex == quizQuestions[currentQuestionIndex].correctAnswer) {
                            score += 5 // 5 points per correct answer
                        }

                        // Move to the next question or end the quiz
                        if (currentQuestionIndex < quizQuestions.size - 1) {
                            currentQuestionIndex++
                            selectedAnswerIndex = null // Reset selected answer
                        } else {
                            isQuizOver = true
                        }
                    },
                    // Disable button if no answer is selected
                    enabled = selectedAnswerIndex != null
                ) {
                    Text("Next")
                }
            }
        }
    }
}

@Composable
fun AnswerOption(
    optionText: String,
    isSelected: Boolean,
    isCorrect: Boolean?,
    onOptionSelected: () -> Unit
) {
    val backgroundColor = when {
        isSelected && isCorrect == true -> Color.Green
        isSelected && isCorrect == false -> Color.Red
        isSelected -> MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
        else -> MaterialTheme.colorScheme.surface
    }

    val textColor = when {
        isSelected && isCorrect == true -> Color.White
        isSelected && isCorrect == false -> Color.White
        else -> MaterialTheme.colorScheme.onSurface
    }

    Button(
        onClick = onOptionSelected,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = textColor
        ),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
    ) {
        Text(text = optionText)
    }
}

@Composable
fun QuizOverScreen(score: Int, totalQuestions: Int) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Quiz Over!", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Your Score: $score / ${totalQuestions * 5}", style = MaterialTheme.typography.titleLarge)
        // You can add more feedback or a button to restart the quiz here
    }
}

// Hardcoded Quiz Data (20 questions)
fun getHardcodedQuizData(): List<QuizQuestion> {
    return listOf(
        QuizQuestion(
            id = 1,
            text = "What is the capital of France?",
            options = listOf("London", "Paris", "Berlin", "Rome"),
            correctAnswer = 1,
            questionType = QuestionType.MULTIPLE_CHOICE
        ),
        QuizQuestion(
            id = 2,
            text = "The Earth is flat.",
            options = listOf("True", "False"),
            correctAnswer = 1,
            questionType = QuestionType.TRUE_FALSE
        ),
        QuizQuestion(
            id = 3,
            text = "What is the highest mountain in the world?",
            options = listOf("K2", "Kangchenjunga", "Mount Everest", "Lhotse"),
            correctAnswer = 2,
            questionType = QuestionType.MULTIPLE_CHOICE
        ),
        QuizQuestion(
            id = 4,
            text = "Water boils at 100 degrees Celsius.",
            options = listOf("True", "False"),
            correctAnswer = 0,
            questionType = QuestionType.TRUE_FALSE
        ),
        QuizQuestion(
            id = 5,
            text = "Who painted the Mona Lisa?",
            options = listOf("Michelangelo", "Leonardo da Vinci", "Raphael", "Donatello"),
            correctAnswer = 1,
            questionType = QuestionType.MULTIPLE_CHOICE
        ),
        QuizQuestion(
            id = 6,
            text = "The Great Wall of China is visible from space.",
            options = listOf("True", "False"),
            correctAnswer = 1,
            questionType = QuestionType.TRUE_FALSE
        ),
        QuizQuestion(
            id = 7,
            text = "What is the smallest country in the world?",
            options = listOf("Monaco", "Vatican City", "Nauru", "Tuvalu"),
            correctAnswer = 1,
            questionType = QuestionType.MULTIPLE_CHOICE
        ),
        QuizQuestion(
            id = 8,
            text = "The human skeleton is made up of 206 bones.",
            options = listOf("True", "False"),
            correctAnswer = 0,
            questionType = QuestionType.TRUE_FALSE
        ),
        QuizQuestion(
            id = 9,
            text = "What is the largest ocean in the world?",
            options = listOf("Atlantic Ocean", "Indian Ocean", "Arctic Ocean", "Pacific Ocean"),
            correctAnswer = 3,
            questionType = QuestionType.MULTIPLE_CHOICE
        ),
        QuizQuestion(
            id = 10,
            text = "The Sahara is the largest desert in the world.",
            options = listOf("True", "False"),
            correctAnswer = 0,
            questionType = QuestionType.TRUE_FALSE
        ),
        QuizQuestion(
            id = 11,
            text = "What is the chemical symbol for gold?",
            options = listOf("Au", "Ag", "Fe", "Hg"),
            correctAnswer = 0,
            questionType = QuestionType.MULTIPLE_CHOICE
        ),
        QuizQuestion(
            id = 12,
            text = "The currency of Japan is the Yen.",
            options = listOf("True", "False"),
            correctAnswer = 0,
            questionType = QuestionType.TRUE_FALSE
        ),
        QuizQuestion(
            id = 13,
            text = "Which planet is known as the 'Red Planet'?",
            options = listOf("Venus", "Mars", "Jupiter", "Saturn"),
            correctAnswer = 1,
            questionType = QuestionType.MULTIPLE_CHOICE
        ),
        QuizQuestion(
            id = 14,
            text = "The human heart has four chambers.",
            options = listOf("True", "False"),
            correctAnswer = 0,
            questionType = QuestionType.TRUE_FALSE
        ),
        QuizQuestion(
            id = 15,
            text = "What is the capital of Australia?",
            options = listOf("Sydney", "Melbourne", "Canberra", "Perth"),
            correctAnswer = 2,
            questionType = QuestionType.MULTIPLE_CHOICE
        ),
        QuizQuestion(
            id = 16,
            text = "All insects have six legs.",
            options = listOf("True", "False"),
            correctAnswer = 0,
            questionType = QuestionType.TRUE_FALSE
        ),
        QuizQuestion(
            id = 17,
            text = "Which animal is known as the 'King of the Jungle'?",
            options = listOf("Tiger", "Lion", "Elephant", "Gorilla"),
            correctAnswer = 1,
            questionType = QuestionType.MULTIPLE_CHOICE
        ),
        QuizQuestion(
            id = 18,
            text = "The first man to walk on the moon was Neil Armstrong.",
            options = listOf("True", "False"),
            correctAnswer = 0,
            questionType = QuestionType.TRUE_FALSE
        ),
        QuizQuestion(
            id = 19,
            text = "What is the longest river in the world?",
            options = listOf("Nile", "Amazon", "Yangtze", "Mississippi"),
            correctAnswer = 0,
            questionType = QuestionType.MULTIPLE_CHOICE
        ),
        QuizQuestion(
            id = 20,
            text = "Mount Fuji is the highest mountain in Japan.",
            options = listOf("True", "False"),
            correctAnswer = 0,
            questionType = QuestionType.TRUE_FALSE
        )
    )
}