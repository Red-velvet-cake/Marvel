package com.red_velvet.marvel.di

import android.content.Context
import androidx.room.Room
import com.red_velvet.marvel.data.local.MarvelDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    private const val DATABASE_NAME = "MarvelDataBase"

    @Singleton
    @Provides
    fun provideMarvelDataBase(@ApplicationContext context: Context): MarvelDataBase {
        return Room.databaseBuilder(
            context,
            MarvelDataBase::class.java,
            DATABASE_NAME).build()
    }

    @Singleton
    @Provides
    fun provideDao(dataBase: MarvelDataBase) = dataBase.dataBaseDao()
}