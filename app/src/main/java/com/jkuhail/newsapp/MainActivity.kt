package com.jkuhail.newsapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import com.jkuhail.newsapp.domain.usecases.AppEntryUseCases
import com.jkuhail.newsapp.presentation.onboarding.OnboardingScreen
import com.jkuhail.newsapp.presentation.onboarding.OnboardingViewModel
import com.jkuhail.newsapp.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var appEntryUseCases: AppEntryUseCases

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        lifecycleScope.launch {
            appEntryUseCases.readAppEntry().collect {
                Log.d("TAG", "onCreate: $it")
            }
        }
        setContent {
            val viewModel: OnboardingViewModel = hiltViewModel()
            NewsAppTheme {
                OnboardingScreen(
                    event = viewModel::onEvent
                )
            }
        }
    }
}