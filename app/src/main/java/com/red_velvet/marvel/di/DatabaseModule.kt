package com.red_velvet.marvel.di

import android.content.Context
import com.red_velvet.marvel.data.local.MarvelDatabase
import com.red_velvet.marvel.data.local.daos.MarvelDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideMarvelDatabase(@ApplicationContext context: Context): MarvelDatabase {
        return MarvelDatabase.getInstance(context)
    }

    @Provides
    fun providesMarvelDao(marvelDatabase: MarvelDatabase): MarvelDao {
        return marvelDatabase.marvelDao()
    }
}