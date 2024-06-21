package com.example.onboarding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.onboarding.main.navigation.Screen
import com.example.onboarding.main.navigation.SetupNavGraph
import com.example.onboarding.ui.theme.OnboardingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OnboardingTheme {
                    val screen = Screen.OnboardingScreen.route
                    val navController = rememberNavController()
                    SetupNavGraph(navController = navController, startDestination = screen)
            }
        }
    }
}