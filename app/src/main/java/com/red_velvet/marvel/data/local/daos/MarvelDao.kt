package com.red_velvet.marvel.data.local.daos

import android.provider.CalendarContract.EventsEntity
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.red_velvet.marvel.data.local.entity.CharacterEntity
import com.red_velvet.marvel.data.local.entity.ComicEntity
import com.red_velvet.marvel.data.local.entity.EventEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

@Dao
interface MarvelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacters(characters:List<CharacterEntity>):Completable

    @Query("SELECT * FROM CharacterEntity")
    fun getAllCharacters():Observable<List<CharacterEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComics(characters:List<ComicEntity>):Completable

    @Query("SELECT * FROM ComicEntity")
    fun getAllComics():Observable<List<ComicEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEvents(characters:List<EventEntity>):Completable

    @Query("SELECT * FROM EventEntity")
    fun getAllEvents():Observable<List<EventEntity>>
}