package com.jkuhail.newsapp.presentation.onboarding

sealed class OnboardingEvent {
    object SaveAppEntry : OnboardingEvent()
}