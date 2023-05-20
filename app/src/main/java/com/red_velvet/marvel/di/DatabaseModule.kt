package com.red_velvet.marvel.di

import android.content.Context
import androidx.room.Room
import com.red_velvet.marvel.data.local.MarvelDao
import com.red_velvet.marvel.data.local.MarvelDatabase
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
        return Room.databaseBuilder(
            context, MarvelDatabase::class.java,
            MarvelDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providesMarvelDao(marvelDatabase: MarvelDatabase): MarvelDao {
        return marvelDatabase.marvelDao()
    }
}