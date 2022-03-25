package com.slvkdev.ruwordle.di

import android.content.Context
import com.slvkdev.spellingchecker.SpellingChecker
import com.slvkdev.ruwordle.utils.DICTIONARY_DATABASE_PATH
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun provideSpellingChecker(@ApplicationContext context: Context) =
        SpellingChecker(context, DICTIONARY_DATABASE_PATH)
}

