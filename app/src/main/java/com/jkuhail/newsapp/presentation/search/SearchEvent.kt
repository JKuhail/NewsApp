package com.jkuhail.newsapp.presentation.search

sealed class SearchEvent {
    data class UpdateSearchQuery(val searchQuery: String) : SearchEvent()
    object PerformSearch : SearchEvent()


}