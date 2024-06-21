package com.example.onboarding.main.navigation

sealed class Screen(val route: String) {
    data object OnboardingScreen : Screen(route = "onboarding_screen")
    data object FinishScreen : Screen(route = "finish_screen")
}