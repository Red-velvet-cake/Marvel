package com.red_velvet.marvel.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.red_velvet.marvel.data.local.database.dao.ComicDao
import com.red_velvet.marvel.data.local.database.entity.ComicEntity
import com.red_velvet.marvel.data.local.database.entity.ThumbnailEntity

@Database(
    entities = [ComicEntity::class, ThumbnailEntity::class],
    version = 1,
)
abstract class MarvelDatabase : RoomDatabase() {
    abstract fun comicDao(): ComicDao
}