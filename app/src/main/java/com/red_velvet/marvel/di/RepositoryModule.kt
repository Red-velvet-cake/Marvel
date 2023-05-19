package com.red_velvet.marvel.di

import com.red_velvet.marvel.data.local.MovieDataBase
import com.red_velvet.marvel.data.remote.MarvelService
import com.red_velvet.marvel.data.repository.MarvelRepository
import com.red_velvet.marvel.data.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.domain.mappers.CharsMapper
import com.red_velvet.marvel.domain.mappers.ComicsMapper
import com.red_velvet.marvel.domain.mappers.EventMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    fun provideMarvelRepository(
        marvelServiceImpl: MarvelService,
        daoMovie: MovieDataBase,
        comicsMapper: ComicsMapper,
        eventMapper: EventMapper,
        charsMapper: CharsMapper
    ): MarvelRepository {
        return MarvelRepositoryImpl(
            marvelServiceImpl,
            daoMovie,
            comicsMapper,
            eventMapper,
            charsMapper
        )
    }
}