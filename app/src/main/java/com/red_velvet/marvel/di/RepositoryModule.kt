package com.red_velvet.marvel.di

import com.red_velvet.marvel.data.local.database.MarvelDatabase
import com.red_velvet.marvel.data.local.mapper.ComicEntityMapper
import com.red_velvet.marvel.data.remote.service.MarvelService
import com.red_velvet.marvel.data.repository.MarvelRepository
import com.red_velvet.marvel.data.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.domain.mapper.LocalComicMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMarvelRepository(
        marvelService: MarvelService,
        marvelDatabase: MarvelDatabase,
    ): MarvelRepository {
        return MarvelRepositoryImpl(
            marvelService,
            marvelDatabase,
        )
    }

    @Singleton
    @Provides
    fun provideComicEntityMapper(): ComicEntityMapper {
        return ComicEntityMapper()
    }

    @Singleton
    @Provides
    fun provideLocalComicMapper(): LocalComicMapper {
        return LocalComicMapper()
    }
}