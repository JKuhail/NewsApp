package com.jkuhail.newsapp.presentation.onboarding.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.jkuhail.newsapp.R
import com.jkuhail.newsapp.presentation.Dimens.PaddingExtraLarge
import com.jkuhail.newsapp.presentation.Dimens.PaddingLarge
import com.jkuhail.newsapp.presentation.onboarding.OnboardingData
import com.jkuhail.newsapp.presentation.onboarding.onboardingPages
import com.jkuhail.newsapp.ui.theme.NewsAppTheme

@Composable
fun OnboardingPage(
    data: OnboardingData,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.6f),
            painter = painterResource(id = data.image),
            contentDescription = data.title,
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(PaddingLarge))
        Text(
            modifier = Modifier.padding(horizontal = PaddingExtraLarge),
            text = data.title,
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
            color = colorResource(id = R.color.display_small)
        )
        Spacer(modifier = Modifier.height(PaddingLarge))
        Text(
            modifier = Modifier.padding(horizontal = PaddingExtraLarge),
            text = data.description,
            style = MaterialTheme.typography.bodyMedium,
            color = colorResource(id = R.color.text_medium)
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun OnboardingPagePreview() {
    NewsAppTheme {
        OnboardingPage(
            data = onboardingPages[0]
        )
    }
}