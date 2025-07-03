package com.jkuhail.newsapp.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.jkuhail.newsapp.R
import com.jkuhail.newsapp.domain.model.Article
import com.jkuhail.newsapp.domain.model.Source
import com.jkuhail.newsapp.presentation.Dimens.ArticleImageSize
import com.jkuhail.newsapp.presentation.Dimens.IconSizeSmall
import com.jkuhail.newsapp.presentation.Dimens.PaddingExtraSmall
import com.jkuhail.newsapp.presentation.Dimens.PaddingSmall
import com.jkuhail.newsapp.ui.theme.NewsAppTheme

@Composable
fun ArticleCard(
    modifier: Modifier = Modifier,
    article: Article,
    onClick: (Article) -> Unit = {},
    ) {
    Row(modifier = modifier.clickable { onClick(article) }) {

        AsyncImage(
            modifier = Modifier
                .size(ArticleImageSize)
                .padding(PaddingSmall)
                .clip(MaterialTheme.shapes.medium),

            contentScale = ContentScale.Crop,
            model = ImageRequest
                .Builder(LocalContext.current)
                .data(article.urlToImage)
                .build(),
            contentDescription = null
        )

        Column(
            modifier = Modifier
                .padding(horizontal = PaddingExtraSmall)
                .height(ArticleImageSize),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = article.title,
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(id = R.color.text_title),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = article.source.name,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(id = R.color.body)
                )
                Spacer(modifier = Modifier.width(PaddingSmall))
                Icon(
                    painter = painterResource(R.drawable.ic_time),
                    contentDescription = null,
                    modifier = Modifier.size(IconSizeSmall),
                    tint = colorResource(id = R.color.body)
                )
                Spacer(modifier = Modifier.width(PaddingSmall))
                Text(
                    text = article.publishedAt,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(id = R.color.body)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun ArticleCardPreview() {
    NewsAppTheme {
        ArticleCard(
            article = Article(
                author = "jerry.hildenbrand@futurenet.com (Jerry Hildenbrand) , Jerry Hildenbrand",
                content = "",
                description = "Keep your money away from thieves by using common sense and good old-fashioned paranoia.",
                publishedAt = "now",
                source = Source(
                    id = "",
                    name = "BBC"
                ),
                title = "Millions will be scammed on Prime Day. Don't be one of them",
                url = "https://www.androidcentral.com/apps-software/how-to-avoid-scams-amazon-prime-day",
                urlToImage = "https://cdn.mos.cms.futurecdn.net/k7c2VW8eHbWtSaCgzLAa4P.jpeg"
            ),
        )
    }
}