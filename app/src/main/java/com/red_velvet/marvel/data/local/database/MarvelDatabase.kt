package com.red_velvet.marvel.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.red_velvet.marvel.data.local.database.dao.CharacterDao
import com.red_velvet.marvel.data.local.database.dao.ComicDao
import com.red_velvet.marvel.data.local.database.dao.EventDao
import com.red_velvet.marvel.data.local.database.entity.CharacterEntity
import com.red_velvet.marvel.data.local.database.entity.ComicEntity
import com.red_velvet.marvel.data.local.database.entity.EventEntity

@Database(
    entities = [ComicEntity::class, EventEntity::class, CharacterEntity::class],
    version = 1,
)
abstract class MarvelDatabase : RoomDatabase() {
    abstract fun comicDao(): ComicDao
    abstract fun eventDao(): EventDao
    abstract fun characterDao(): CharacterDao
}