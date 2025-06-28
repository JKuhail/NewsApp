package com.jkuhail.newsapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jkuhail.newsapp.domain.usecases.AppEntryUseCases
import com.jkuhail.newsapp.presentation.navgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    appEntryUseCases: AppEntryUseCases
) : ViewModel() {

    var splashCondition by mutableStateOf(true)
        private set

    var startDestination by mutableStateOf(Route.AppStartNavigation.name)
        private set

    init {
        viewModelScope.launch {
            appEntryUseCases.readAppEntry().collect { shouldStartFromHomeScreen ->
                startDestination = if (shouldStartFromHomeScreen) {
                    Route.NewsNavigation.name
                } else {
                    Route.AppStartNavigation.name
                }
                delay(300)
                splashCondition = false
            }
        }

    }
}