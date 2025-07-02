package com.jkuhail.newsapp.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.jkuhail.newsapp.data.manager.LocalUserManagerImpl
import com.jkuhail.newsapp.data.remote.NewsApi
import com.jkuhail.newsapp.data.repository.NewsRepositoryImp
import com.jkuhail.newsapp.domain.manager.LocalUserManager
import com.jkuhail.newsapp.domain.repository.NewsRepository
import com.jkuhail.newsapp.domain.usecases.app_entry.AppEntryUseCases
import com.jkuhail.newsapp.domain.usecases.app_entry.ReadAppEntry
import com.jkuhail.newsapp.domain.usecases.app_entry.SaveAppEntry
import com.jkuhail.newsapp.domain.usecases.news.GetNews
import com.jkuhail.newsapp.domain.usecases.news.NewsUseCases
import com.jkuhail.newsapp.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val Context.dataStore: DataStore<Preferences>
            by preferencesDataStore(name = Constants.USER_SETTINGS)

    @Provides
    @Singleton
    fun provideDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> {
        return context.dataStore
    }

    @Provides
    @Singleton
    fun provideLocalUserManager(
        dataStore: DataStore<Preferences>
    ): LocalUserManager =
        LocalUserManagerImpl(dataStore)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(localUserManager: LocalUserManager) =
        AppEntryUseCases(
            readAppEntry = ReadAppEntry(localUserManager),
            saveAppEntry = SaveAppEntry(localUserManager)
        )

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi
    ): NewsRepository = NewsRepositoryImp(newsApi)

    @Provides
    @Singleton
    fun provideNewsUseCases(newsRepository: NewsRepository) = NewsUseCases(
        getNews = GetNews(newsRepository = newsRepository)
    )
}