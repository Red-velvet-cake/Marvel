package com.red_velvet.marvel.di

import android.content.Context
import androidx.room.Room
import com.red_velvet.marvel.data.local.database.MarvelDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideMarvelDatabase(@ApplicationContext context: Context): MarvelDatabase {
        return Room.databaseBuilder(
            context,
            MarvelDatabase::class.java,
            "MarvelDatabase"
        ).build()
    }

    @Singleton
    @Provides
    fun provideComicDao(marvelDatabase: MarvelDatabase) = marvelDatabase.comicDao()

    @Singleton
    @Provides
    fun provideCharacterDao(marvelDatabase: MarvelDatabase) = marvelDatabase.characterDao()

    @Singleton
    @Provides
    fun provideEventDao(marvelDatabase: MarvelDatabase) = marvelDatabase.eventDao()
}