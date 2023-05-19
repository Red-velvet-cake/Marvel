package com.red_velvet.marvel.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.red_velvet.marvel.data.local.database.entity.CharacterEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacter(character: CharacterEntity): Completable

    @Query("SELECT * FROM CHARACTER_TABLE")
    fun getAllCharacters(): Observable<List<CharacterEntity>>
}