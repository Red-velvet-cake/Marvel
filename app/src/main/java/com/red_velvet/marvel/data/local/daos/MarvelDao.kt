package com.red_velvet.marvel.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.red_velvet.marvel.data.local.entity.CharacterEntity
import com.red_velvet.marvel.data.local.entity.ComicEntity
import com.red_velvet.marvel.data.local.entity.EventEntity
import io.reactivex.rxjava3.core.Observable

@Dao
interface MarvelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComics(comicEntity: List<ComicEntity>)

    @Query("SELECT * FROM COMICS")
    fun getAllComics(): Observable<List<ComicEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacters(characterEntity: List<CharacterEntity>)

    @Query("SELECT * FROM CHARACTERS")
    fun getAllCharacters(): Observable<List<CharacterEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEvents(eventEntity: List<EventEntity>)

    @Query("SELECT * FROM EVENTS")
    fun getAllEvents(): Observable<List<EventEntity>>
}