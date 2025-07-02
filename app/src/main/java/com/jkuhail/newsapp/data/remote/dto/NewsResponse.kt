package com.jkuhail.newsapp.data.remote.dto

import com.jkuhail.newsapp.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)