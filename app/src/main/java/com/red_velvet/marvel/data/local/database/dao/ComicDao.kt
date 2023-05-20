package com.red_velvet.marvel.data.local.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.red_velvet.marvel.data.local.database.entity.ComicEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

@Dao
interface ComicDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(comic: ComicEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(comics: List<ComicEntity>)

    @Query("SELECT * FROM COMIC_TABLE")
    fun getAll(): Observable<List<ComicEntity>>

    @Delete
    fun delete(comic: ComicEntity): Completable

}