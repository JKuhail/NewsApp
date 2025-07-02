package com.jkuhail.newsapp.presentation.navgraph

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.paging.compose.collectAsLazyPagingItems
import com.jkuhail.newsapp.presentation.home.HomeScreen
import com.jkuhail.newsapp.presentation.home.HomeViewModel
import com.jkuhail.newsapp.presentation.onboarding.OnboardingScreen
import com.jkuhail.newsapp.presentation.onboarding.OnboardingViewModel

@Composable
fun NavGraph(
    startDestination: String,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = rememberNavController(),
        startDestination = startDestination,
        modifier = modifier
    ) {
        /**
         * Onboarding nested nav graph
         */
        navigation(
            route = Route.AppStartNavigation.name,
            startDestination = Route.OnboardingScreen.name
        ) {
            composable(route = Route.OnboardingScreen.name) {
                val viewModel: OnboardingViewModel = hiltViewModel()
                OnboardingScreen(event = viewModel::onEvent)
            }
        }

        /**
         * News Navigation
         */
        navigation(
            route = Route.NewsNavigation.name,
            startDestination = Route.NewsMainScreen.name
        ) {
            composable(route = Route.NewsMainScreen.name) {
                val viewModel: HomeViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()
                HomeScreen(articles = articles) {

                }
            }
        }
    }
}