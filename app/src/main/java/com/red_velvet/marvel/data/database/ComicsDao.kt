package com.red_velvet.marvel.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.red_velvet.marvel.data.Comics
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface ComicsDao {
    @Insert
    fun insertComics(comics: Comics):Completable

    @Query("SELECT * FROM COMICS_TABLE")
    fun getAllComics() :Single<List<Comics>>
}