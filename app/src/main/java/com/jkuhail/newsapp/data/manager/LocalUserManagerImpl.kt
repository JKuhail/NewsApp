package com.jkuhail.newsapp.data.manager

import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.jkuhail.newsapp.domain.manager.LocalUserManager
import com.jkuhail.newsapp.util.Constants
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class LocalUserManagerImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : LocalUserManager {
    override suspend fun saveAppEntry() {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.APP_ENTRY] = true
        }
    }

    override fun readAppEntry(): Flow<Boolean> {
        return dataStore.data
            .catch {
                if (it is IOException) emit(emptyPreferences())
                else throw it
            }
            .map { preferences ->
                preferences[PreferencesKeys.APP_ENTRY] ?: false
            }
    }
}

private object PreferencesKeys {
    val APP_ENTRY = booleanPreferencesKey(name = Constants.APP_ENTRY)

}