package com.red_velvet.marvel.di

import android.content.Context
import com.red_velvet.marvel.data.local.MovieDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieDataBaseModule {

    @Provides
    @Singleton
    fun provideMovieDataBase(context: Context): MovieDataBase {
        return MovieDataBase.getInstance(context)
    }
}