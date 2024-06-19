@file:OptIn(
    ExperimentalFoundationApi::class, ExperimentalFoundationApi::class,
    ExperimentalFoundationApi::class, ExperimentalFoundationApi::class
)

package com.example.onboarding.main

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.onboarding.ui.theme.OnboardingTheme

@ExperimentalFoundationApi
@Composable
fun OnBoardScreen() {
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
        animationSpec = tween(durationMillis = 500), label = ""
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = animatedColor
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            HorizontalPager(state = pagerState) { position ->
                PagerScreen(pagesList[position])
            }

            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
            )
        }
    }
}

@Composable
fun HorizontalPagerIndicator(pagerState: PagerState, modifier: Modifier) {
    val rowModifier = modifier
        .wrapContentHeight()
        .fillMaxWidth()
        .padding(bottom = 8.dp)
    Row(
        modifier = rowModifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(pagerState.pageCount) { iteration ->

            val isSelected = pagerState.currentPage == iteration

            val color =
                if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray

            val shape =
                if (pagerState.currentPage == iteration) RoundedCornerShape(8.dp) else CircleShape

            val offset = pagerState.currentPageOffsetFraction

            val width: Float by animateFloatAsState(
                targetValue = if (isSelected) 24f else 12f,
                animationSpec = tween(
                    durationMillis = 500,
                ),
                label = ""
            )

            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(shape)
                    .background(color)
                    .height(12.dp)
                    .width(width.dp)
            )
        }
    }

}

@Composable
fun ProgressButton() {

    Box {

        CircularProgressIndicator(
            modifier = Modifier
                .size(64.dp)
                .align(Alignment.Center),
            progress = 0.2f,
            trackColor = Color.Black,
            color = Color.Green
        )
        ElevatedButton(
            onClick = { },
            shape = CircleShape,
            modifier = Modifier
                .size(42.dp)
                .align(Alignment.Center)
        ) {

        }
    }

}

@Composable
@Preview
fun ProgressButtonPreview() {
    ProgressButton()
}

@Composable
@Preview
fun OnBoardScreenPreview() {
    OnboardingTheme {
        OnBoardScreen()
    }
}