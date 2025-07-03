package com.jkuhail.newsapp.presentation.search

import androidx.paging.PagingData
import com.jkuhail.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery: String = "",
    val searchResults: Flow<PagingData<Article>>? = null
)
