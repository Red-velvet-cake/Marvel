package com.red_velvet.marvel.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.red_velvet.marvel.data.local.entity.ComicsEntity
import com.red_velvet.marvel.data.local.entity.SearchCharacterResultEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface DataBaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(comic: List<ComicsEntity>): Completable

    @Query("SELECT * FROM Comics_table WHERE id = :id")
    fun getComic(id: Long): Single<ComicsEntity>

    @Query("SELECT * FROM Comics_table")
    fun getAllComics(): Single<List<ComicsEntity>>

    @Query("SELECT * FROM Comics_table WHERE dateDescriptor = :dateDescriptor")
    fun getComicsByDateDescriptor(dateDescriptor: String?): Single<List<ComicsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSearchCharacterResult(item: List<SearchCharacterResultEntity>) : Completable

    @Query("Select * From searchCharacterResult_table")
    fun getSearchCharacterResult(): Single<List<SearchCharacterResultEntity>>

    @Query("Select * From searchCharacterResult_table ORDER BY id DESC LIMIT 1")
    fun getLastSearchCharacterResult(): Single<List<SearchCharacterResultEntity>>

    @Query("Select * From searchCharacterResult_table Where name = :name")
    fun getSearchCharacterResultByName(name: String): Single<List<SearchCharacterResultEntity>>

}