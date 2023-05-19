package com.red_velvet.marvel.di

import com.red_velvet.marvel.data.local.MarvelDataBase
import com.red_velvet.marvel.data.local.dao.DataBaseDao
import com.red_velvet.marvel.data.remote.MarvelService
import com.red_velvet.marvel.domain.repository.MarvelRepository
import com.red_velvet.marvel.domain.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.domain.mapper.comics.ComicDtoToComicsEntityMapper
import com.red_velvet.marvel.domain.mapper.comics.ComicsEntityToComicsMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideRepository(
        marvelDataBaseDao: DataBaseDao,
        apiService: MarvelService,
        comicDtoToComicsEntityMapper: ComicDtoToComicsEntityMapper,
        comicsEntityToComicsMapper: ComicsEntityToComicsMapper
    ): MarvelRepository {
        return MarvelRepositoryImpl(
            apiService,
            marvelDataBaseDao,
            comicDtoToComicsEntityMapper,
            comicsEntityToComicsMapper)
    }

    @Provides
    fun provideComicsEntityMapper() = ComicDtoToComicsEntityMapper()
    @Provides
    fun provideComicsMapper() = ComicsEntityToComicsMapper()
}