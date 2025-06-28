package com.jkuhail.newsapp.presentation.onboarding

import androidx.annotation.DrawableRes
import com.jkuhail.newsapp.R

data class OnboardingData(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

val onboardingPages = listOf(
    OnboardingData(
        title = "Welcome to our app!",
        description = "This is a sample onboarding screen",
        image = R.drawable.onboarding1
    ),
    OnboardingData(
        title = "Welcome to our app!",
        description = "This is a sample onboarding screen",
        image = R.drawable.onboarding2
    ),
    OnboardingData(
        title = "Welcome to our app!",
        description = "This is a sample onboarding screen",
        image = R.drawable.onboarding3
    )
)
