package com.jkuhail.newsapp.domain.usecases

import com.jkuhail.newsapp.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke(): Flow<Boolean> = localUserManager.readAppEntry()
}