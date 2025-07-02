package com.jkuhail.newsapp.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.jkuhail.newsapp.R
import com.jkuhail.newsapp.domain.model.Article
import com.jkuhail.newsapp.presentation.Dimens.PaddingLarge
import com.jkuhail.newsapp.presentation.Dimens.PaddingSmall
import com.jkuhail.newsapp.presentation.common.ArticlesList
import com.jkuhail.newsapp.presentation.common.SearchBar
import com.jkuhail.newsapp.presentation.navgraph.Route

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    articles: LazyPagingItems<Article>,
    navigateToDetails: (String) -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = PaddingLarge)
            .statusBarsPadding()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = null,
            modifier = Modifier
                .width(150.dp)
                .height(30.dp)
                .padding(horizontal = PaddingLarge)
        )
        Spacer(modifier = Modifier.height(PaddingLarge))
        SearchBar(
            modifier = Modifier.padding(horizontal = PaddingLarge),
            text = "",
            readOnly = true,
            onValueChange = {},
            onSearch = {},
            onClick = {
                navigateToDetails(Route.SearchScreen.name)
            }
        )
        Spacer(modifier = Modifier.height(PaddingLarge))

        ArticlesList(
            modifier = Modifier.padding(horizontal = PaddingLarge),
            articles = articles
        ) {
            navigateToDetails(Route.DetailsScreen.name)
        }
    }
}