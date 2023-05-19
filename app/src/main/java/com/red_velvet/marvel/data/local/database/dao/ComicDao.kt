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
    fun insertComic(comic: ComicEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllComics(comics: List<ComicEntity>): Completable

    @Query("SELECT * FROM COMIC_TABLE")
    fun getAllComics(): Observable<List<ComicEntity>>

    @Delete
    fun deleteComic(comic: ComicEntity): Completable

}