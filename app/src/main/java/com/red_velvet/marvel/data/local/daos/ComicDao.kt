package com.red_velvet.marvel.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.red_velvet.marvel.data.local.entities.CharacterEntity
import com.red_velvet.marvel.data.local.entities.ComicEntity
import com.red_velvet.marvel.data.local.entities.EventEntity
import io.reactivex.rxjava3.core.Observable

@Dao
interface ComicDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComic(comicEntity: ComicEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEvent(eventEntity: EventEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacter(characterEntity: CharacterEntity)

    @Query("SELECT * FROM COMICENTITY")
    fun getAllComics(): Observable<ComicEntity>

    @Query("SELECT * FROM CHARACTERENTITY")
    fun getAllCharacters(): Observable<CharacterEntity>

    @Query("SELECT * FROM EVENTENTITY")
    fun getAllEvents(): Observable<EventEntity>
}