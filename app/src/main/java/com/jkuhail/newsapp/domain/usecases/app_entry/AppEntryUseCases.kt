package com.jkuhail.newsapp.domain.usecases.app_entry

/**
 * UseCase Holder (Optional)
 * This helps if you're injecting multiple use cases.
 */
data class AppEntryUseCases(
    val readAppEntry: ReadAppEntry,
    val saveAppEntry: SaveAppEntry
)
