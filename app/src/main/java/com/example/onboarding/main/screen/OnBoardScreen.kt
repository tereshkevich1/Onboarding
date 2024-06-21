@file:OptIn(
    ExperimentalFoundationApi::class,
    ExperimentalFoundationApi::class,
    ExperimentalFoundationApi::class,
    ExperimentalFoundationApi::class
)

package com.example.onboarding.main.screen

import android.app.Activity
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.onboarding.R
import com.example.onboarding.main.navigation.Screen
import com.example.onboarding.main.util.OnBoardingPage
import com.example.onboarding.ui.theme.OnboardingTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@Composable
fun OnBoardScreen(navController: NavController) {
    val pagesList = listOf(
        OnBoardingPage.OnBoarding1,
        OnBoardingPage.OnBoarding2,
        OnBoardingPage.OnBoarding3,
        OnBoardingPage.OnBoarding4
    )
    val pagerState = rememberPagerState(pageCount = { pagesList.size })

    val currentBackgroundColor = colorResource(pagesList[pagerState.currentPage].backgroundColor)
    val animatedColor by animateColorAsState(
        targetValue = currentBackgroundColor,
        animationSpec = tween(durationMillis = 500),
        label = ""
    )
    val progress by remember {
        derivedStateOf {
            (pagerState.currentPage + 1) / pagesList.size.toFloat()
        }
    }

    SetSystemBarsColor(animatedColor)

    Surface(
        modifier = Modifier.fillMaxSize(), color = animatedColor
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            HorizontalPager(state = pagerState) { position ->
                PagerScreen(pagesList[position])

            }
            Spacer(modifier = Modifier.weight(1f))
            OnBoardingFooter(
                pagerState = pagerState,
                animatedColor = animatedColor,
                progress = progress,
                navController = navController
            )
        }
    }
}

@Composable
fun OnBoardingFooter(
    pagerState: PagerState, animatedColor: Color, progress: Float, navController: NavController
) {
    val scope = rememberCoroutineScope()
    val rowModifier = Modifier
        .fillMaxWidth()
        .padding(
            start = dimensionResource(id = R.dimen.onboarding_message_column_horizontal_padding),
            end = dimensionResource(id = R.dimen.onboarding_message_column_horizontal_padding),
            bottom = 6.dp
        )
    Row(modifier = rowModifier) {
        Column(modifier = Modifier.align(Alignment.CenterVertically)) {
            HorizontalPagerIndicator(pagerState = pagerState)
            ClickableText(
                text = AnnotatedString(stringResource(id = R.string.skip)),
                onClick = {
                    navigateToFinishScreen(
                        navController
                    )
                },
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        ProgressButton(
            animatedColor, progress
        ) { navigateToNextPage(scope, pagerState, navController) }
    }
}

@Composable
fun HorizontalPagerIndicator(pagerState: PagerState) {
    val rowModifier = Modifier
        .wrapContentHeight()
        .padding(bottom = 16.dp)
    Row(
        modifier = rowModifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(pagerState.pageCount) { iteration ->

            val activeColor = colorResource(id = R.color.white)
            val passiveColor = activeColor.copy(alpha = 0.4f)

            val isSelected = pagerState.currentPage == iteration
            val color = if (pagerState.currentPage == iteration) activeColor else passiveColor

            val width: Float by animateFloatAsState(
                targetValue = if (isSelected) 24f else 8f, animationSpec = tween(
                    durationMillis = 500,
                ), label = ""
            )

            val boxModifier = Modifier
                .clip(CircleShape)
                .background(color)
                .height(8.dp)
                .width(width.dp)

            Box(modifier = boxModifier)
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}

@Composable
fun ProgressButton(contentButtonColor: Color, progress: Float, onProgressButtonClick: () -> Unit) {

    val containerButtonColor = colorResource(id = R.color.white)
    val progressBackgroundColor = colorResource(id = R.color.progress_indicator_tracker_color)
    val trackColor = progressBackgroundColor.copy(alpha = 0.4f)

    val iconButtonColors = IconButtonDefaults.iconButtonColors(
        containerColor = containerButtonColor, contentColor = contentButtonColor
    )
    val animatedProgress: Float by animateFloatAsState(
        targetValue = progress, animationSpec = tween(
            durationMillis = 800,
        ), label = ""
    )

    Box {
        CircularProgressIndicator(
            modifier = Modifier
                .size(64.dp)
                .align(Alignment.Center),
            progress = animatedProgress,
            trackColor = trackColor,
            color = progressBackgroundColor
        )
        IconButton(
            onClick = onProgressButtonClick,
            modifier = Modifier
                .size(42.dp)
                .align(Alignment.Center)
                .clip(CircleShape),
            colors = iconButtonColors
        ) {
            Icon(
                painter = painterResource(id = R.drawable.right_arrow), contentDescription = "next"
            )
        }
    }
}

@Composable
fun SetSystemBarsColor(color: Color) {
    val view = LocalView.current
    LaunchedEffect(color) {
        val activity = view.context as Activity
        activity.window.statusBarColor = color.toArgb()
        activity.window.navigationBarColor = color.toArgb()
    }
}


fun navigateToNextPage(
    scope: CoroutineScope, pagerState: PagerState, navController: NavController
) {
    scope.launch {
        if (pagerState.currentPage < pagerState.pageCount - 1) {
            pagerState.animateScrollToPage(pagerState.currentPage + 1)
        } else {
            navController.navigate(Screen.FinishScreen.route)
        }
    }
}

fun navigateToFinishScreen(
    navController: NavController
) {
    navController.navigate(Screen.FinishScreen.route)
}

@Composable
@Preview
fun OnBoardScreenPreview() {
    OnboardingTheme {
        OnBoardScreen(rememberNavController())
    }
}