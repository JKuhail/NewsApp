package com.jkuhail.newsapp.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.jkuhail.newsapp.data.manager.LocalUserManagerImpl
import com.jkuhail.newsapp.domain.manager.LocalUserManager
import com.jkuhail.newsapp.domain.usecases.AppEntryUseCases
import com.jkuhail.newsapp.domain.usecases.ReadAppEntry
import com.jkuhail.newsapp.domain.usecases.SaveAppEntry
import com.jkuhail.newsapp.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
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
}