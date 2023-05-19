package com.red_velvet.marvel.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.red_velvet.marvel.data.domain.model.CharacterEntity
import com.red_velvet.marvel.data.domain.model.ComicEntity
import com.red_velvet.marvel.data.domain.model.EventEntity

@Dao
interface HomeScreenDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComic(comicEntity: ComicEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEvent(eventEntity: EventEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacter(characterEntity: CharacterEntity)

 /*   @Query("SELECT * FROM ComicEntity")
    fun getAllComics(): Observable<List<ComicEntity>>

    @Query("SELECT * FROM CharacterEntity")
    fun getAllCharacters(): Observable<List<CharacterEntity>>

    @Query("SELECT * FROM EventEntity")
    fun getAllEvents(): Observable<List<EventEntity>>*/
}