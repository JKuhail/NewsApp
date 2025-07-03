package com.jkuhail.newsapp.presentation.search

import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.jkuhail.newsapp.domain.usecases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val newsUseCase: NewsUseCases
) : ViewModel() {

    var searchState = mutableStateOf(SearchState())
        private set

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.UpdateSearchQuery -> {
                searchState.value = searchState.value.copy(searchQuery = event.searchQuery)
            }

            is SearchEvent.PerformSearch -> {
                performSearch()

            }
        }
    }

    private fun performSearch() {
        val articles = newsUseCase.searchNews(
            searchQuery = searchState.value.searchQuery,
            sources = listOf("bbc-news", "abc-news", "al-jazeera-english")
        ).cachedIn(viewModelScope)
        searchState.value = searchState.value.copy(searchResults = articles)
    }

}