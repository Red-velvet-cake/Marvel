package com.red_velvet.marvel.data.local.daos

import android.database.Observable
import androidx.room.Dao
import androidx.room.Query
import com.red_velvet.marvel.data.local.entities.CharacterEntity
import com.red_velvet.marvel.data.local.entities.ComicEntity
import com.red_velvet.marvel.data.local.entities.EventEntity

@Dao
interface ComicDao {
    @Query("SELECT * FROM COMICENTITY")
    fun getAllComics(): Observable<ComicEntity>

    @Query("SELECT * FROM CHARACTERENTITY")
    fun getAllCharacters(): Observable<CharacterEntity>

    @Query("SELECT * FROM EVENTENTITY")
    fun getAllEvents(): Observable<EventEntity>
}