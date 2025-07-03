package com.jkuhail.newsapp.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.paging.compose.collectAsLazyPagingItems
import com.jkuhail.newsapp.presentation.Dimens.PaddingLarge
import com.jkuhail.newsapp.presentation.common.ArticlesList
import com.jkuhail.newsapp.presentation.common.SearchBar
import com.jkuhail.newsapp.presentation.navgraph.Route

@Composable
fun SearchScreen(
    state: SearchState,
    eventHandler: (SearchEvent) -> Unit,
    navigate: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = modifier
            .padding(top = PaddingLarge, start = PaddingLarge, end = PaddingLarge)
            .statusBarsPadding()
    ) {
        SearchBar(
            text = state.searchQuery,
            readOnly = false,
            onValueChange = {
                eventHandler(SearchEvent.UpdateSearchQuery(it))
            },
            onSearch = {
                eventHandler(SearchEvent.PerformSearch)
                keyboardController?.hide()
            }
        )
        Spacer(modifier = Modifier.padding(top = PaddingLarge))
        state.searchResults?.let {
            val articles = it.collectAsLazyPagingItems()
            ArticlesList(
                articles = articles,
            ) { navigate(Route.DetailsScreen.name) }
        }
    }

}