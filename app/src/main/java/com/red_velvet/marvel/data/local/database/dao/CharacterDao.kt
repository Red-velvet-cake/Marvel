package com.red_velvet.marvel.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.red_velvet.marvel.data.local.database.entity.CharacterEntity
import io.reactivex.rxjava3.core.Observable

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(character: CharacterEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(characters: List<CharacterEntity>)

    @Query("SELECT * FROM CHARACTER_TABLE")
    fun getAll(): Observable<List<CharacterEntity>>
}