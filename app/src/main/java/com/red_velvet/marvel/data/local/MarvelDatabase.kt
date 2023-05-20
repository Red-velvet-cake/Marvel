package com.red_velvet.marvel.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.red_velvet.marvel.data.local.entity.CharacterEntity
import com.red_velvet.marvel.data.local.entity.ComicEntity
import com.red_velvet.marvel.data.local.entity.EventEntity
import com.red_velvet.marvel.data.local.entity.SearchQueryEntity
import com.red_velvet.marvel.data.local.entity.SeriesEntity

@Database(
    entities = [ComicEntity::class, CharacterEntity::class, EventEntity::class, SeriesEntity::class, SearchQueryEntity::class],
    version = 1
)
abstract class MarvelDatabase : RoomDatabase() {

    abstract fun marvelDao(): MarvelDao

    companion object {
        private const val DATABASE_NAME = "MyDatabase"

        @Volatile
        private var instance: MarvelDatabase? = null

        fun getInstance(context: Context): MarvelDatabase {
            return instance ?: synchronized(this) { buildDatabase(context).also { instance = it } }
        }

        private fun buildDatabase(context: Context): MarvelDatabase {
            return Room.databaseBuilder(context, MarvelDatabase::class.java, DATABASE_NAME).build()
        }
    }
}