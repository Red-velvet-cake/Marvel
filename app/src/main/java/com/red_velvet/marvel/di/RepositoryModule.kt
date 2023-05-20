package com.red_velvet.marvel.di

import com.red_velvet.marvel.data.local.daos.MarvelDao
import com.red_velvet.marvel.data.remote.MarvelService
import com.red_velvet.marvel.domain.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.domain.mappers.CharacterEntityMapper
import com.red_velvet.marvel.domain.mappers.CharacterMapper
import com.red_velvet.marvel.domain.mappers.ComicEntityMapper
import com.red_velvet.marvel.domain.mappers.ComicMapper
import com.red_velvet.marvel.domain.mappers.EventEntityMapper
import com.red_velvet.marvel.domain.mappers.EventMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideMarvelRepositoryImp(
        apiService: MarvelService,
        marvelDao: MarvelDao,
        comicEntityMapper: ComicEntityMapper,
        comicMapper: ComicMapper,
        characterEntityMapper: CharacterEntityMapper,
        characterMapper: CharacterMapper,
        eventEntityMapper: EventEntityMapper,
        eventMapper: EventMapper
    ): MarvelRepositoryImpl {
        return MarvelRepositoryImpl(
            apiService,
            marvelDao,
            comicEntityMapper,
            comicMapper,
            characterEntityMapper,
            characterMapper,
            eventEntityMapper,
            eventMapper
        )
    }
}