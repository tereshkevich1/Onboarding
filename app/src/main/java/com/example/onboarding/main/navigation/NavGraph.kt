@file:OptIn(ExperimentalFoundationApi::class)

package com.example.onboarding.main.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.onboarding.main.screen.FinishScreen
import com.example.onboarding.main.screen.OnBoardScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable(route = Screen.OnboardingScreen.route) {
            OnBoardScreen(navController = navController)
        }
        composable(route = Screen.FinishScreen.route) {
            FinishScreen()
        }
    }

}