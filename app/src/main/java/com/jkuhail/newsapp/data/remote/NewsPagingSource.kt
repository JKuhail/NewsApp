package com.jkuhail.newsapp.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jkuhail.newsapp.domain.model.Article

class NewsPagingSource(
    private val newsApi: NewsApi,
    private val sources: String
) : PagingSource<Int, Article>() {

    private var totalNewsCount = 0

    /**
     * Refresh key is used for subsequent refresh calls to PagingSource.load after the initial load
     * or if the user refreshes the screen (which is not supported currently).
     * [This is a standard block, you can use the exact same implementation for all PagingSource.]
     */
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val page = params.key ?: 1
            val newsResponse = newsApi.getNews(sources = sources, page = page)
            totalNewsCount += newsResponse.articles.size

            val articles = newsResponse.articles.distinctBy { it.title }

            LoadResult.Page(
                data = articles,
                nextKey = if (totalNewsCount == newsResponse.totalResults) null else page + 1,
                prevKey = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(throwable = e)
        }
    }
}