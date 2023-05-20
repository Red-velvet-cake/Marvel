package com.red_velvet.marvel.di

import com.red_velvet.marvel.data.local.database.MarvelDatabase
import com.red_velvet.marvel.data.local.mapper.CharacterEntityMapper
import com.red_velvet.marvel.data.local.mapper.ComicEntityMapper
import com.red_velvet.marvel.data.local.mapper.EventEntityMapper
import com.red_velvet.marvel.data.remote.service.MarvelService
import com.red_velvet.marvel.data.repository.MarvelRepository
import com.red_velvet.marvel.data.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.domain.mapper.LocalCharacterMapper
import com.red_velvet.marvel.domain.mapper.LocalComicMapper
import com.red_velvet.marvel.domain.mapper.LocalEventMapper
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
        comicsEntityMapper: ComicEntityMapper,
        localComicMapper: LocalComicMapper,
        eventEntityMapper: EventEntityMapper,
        localEventMapper: LocalEventMapper,
        characterEntityMapper: CharacterEntityMapper,
        localCharacterMapper: LocalCharacterMapper,
    ): MarvelRepository {
        return MarvelRepositoryImpl(
            marvelService,
            marvelDatabase,
            comicsEntityMapper,
            localComicMapper,
            eventEntityMapper,
            localEventMapper,
            characterEntityMapper,
            localCharacterMapper,
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

    @Singleton
    @Provides
    fun provideEventEntityMapper(): EventEntityMapper {
        return EventEntityMapper()
    }

    @Singleton
    @Provides
    fun provideLocalEventMapper(): LocalEventMapper {
        return LocalEventMapper()
    }

    @Singleton
    @Provides
    fun provideCharacterEntityMapper(): CharacterEntityMapper {
        return CharacterEntityMapper()
    }

    @Singleton
    @Provides
    fun provideLocalCharacterMapper(): LocalCharacterMapper {
        return LocalCharacterMapper()
    }
}