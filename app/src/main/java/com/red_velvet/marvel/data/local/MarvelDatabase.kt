package com.red_velvet.marvel.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.red_velvet.marvel.data.local.entity.CharacterEntity
import com.red_velvet.marvel.data.local.entity.ComicEntity
import com.red_velvet.marvel.data.local.entity.EventEntity
import com.red_velvet.marvel.data.local.entity.SearchQueryEntity
import com.red_velvet.marvel.data.local.entity.SeriesEntity

@Database(
    entities = [
        ComicEntity::class,
        CharacterEntity::class,
        EventEntity::class,
        SeriesEntity::class,
        SearchQueryEntity::class
    ],
    version = 1
)
abstract class MarvelDatabase : RoomDatabase() {

    abstract fun marvelDao(): MarvelDao

    companion object {
        const val DATABASE_NAME = "MyDatabase"
    }
}