package com.example.onboarding.main.screen

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.onboarding.R
import com.example.onboarding.main.util.OnBoardingPage
import com.example.onboarding.ui.theme.OnboardingTheme

@Composable
fun PagerScreen(page: OnBoardingPage) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        MessageColumn(
            title = page.title,
            text = page.text
        )
        Image(
            painter = painterResource(id = page.image),
            contentDescription = "",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .fillMaxHeight(0.8f)
                .align(page.imageAlignment)
        )
    }
}

@Composable
fun MessageColumn(@StringRes title: Int, @StringRes text: Int) {
    val columnModifier = Modifier.padding(
        start = dimensionResource(id = R.dimen.onboarding_message_column_horizontal_padding),
        end = dimensionResource(id = R.dimen.onboarding_message_column_horizontal_padding),
        top = dimensionResource(id = R.dimen.onboarding_message_column_top_padding),
        bottom = dimensionResource(id = R.dimen.onboarding_message_column_bottom_padding)
    )

    val textModifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.text_padding))

    Column(
        modifier = columnModifier
    ) {
        Text(
            text = stringResource(id = title),
            style = MaterialTheme.typography.titleMedium,
            modifier = textModifier
        )
        Text(text = stringResource(id = text), style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true, showBackground = true)
fun Onboarding1DarkPreview() {
    OnboardingTheme {
        PagerScreen(OnBoardingPage.OnBoarding2)
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun Onboarding1LightDarkPreview() {
    OnboardingTheme {
        PagerScreen(OnBoardingPage.OnBoarding2)
    }
}