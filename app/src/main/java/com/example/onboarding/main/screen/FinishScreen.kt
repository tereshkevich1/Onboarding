package com.example.onboarding.main.screen

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.onboarding.R
import com.example.onboarding.ui.theme.OnboardingTheme

@Composable
fun FinishScreen() {

    val backgroundColor = colorResource(id = R.color.onboarding_4_background_color)
    val animatedColor by animateColorAsState(
        targetValue = backgroundColor,
        animationSpec = tween(durationMillis = 500),
        label = ""
    )


    SetSystemBarsColor(color = animatedColor)

    Surface(color = animatedColor ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = stringResource(id = R.string.finish_screen_message),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(bottom = dimensionResource(id = R.dimen.finish_screen_message_padding))
            )
        }
    }
}

@Composable
@Preview
fun FinishScreenPreview() {
    OnboardingTheme {
        FinishScreen()
    }
}