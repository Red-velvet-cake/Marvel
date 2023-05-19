package com.red_velvet.marvel.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.red_velvet.marvel.data.local.dao.DataBaseDao
import com.red_velvet.marvel.data.local.entity.ComicsEntity
import com.red_velvet.marvel.data.local.entity.SearchCharacterResultEntity


@Database(
    entities = [ComicsEntity::class,
    SearchCharacterResultEntity::class],
    version = 1)
abstract class MarvelDataBase: RoomDatabase() {
    abstract fun dataBaseDao(): DataBaseDao
}