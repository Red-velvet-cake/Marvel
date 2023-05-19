package com.red_velvet.marvel.data.local.dao

import androidx.room.Insert
import androidx.room.Query
import com.red_velvet.marvel.data.entity.Events
import com.red_velvet.marvel.data.entity.SeriesSearch
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface SeriesDao {

    @Insert
    fun insertSeries(seriesSearch: SeriesSearch): Completable

    @Query("SELECT * FROM SERIES_SEARCH_TABLE")
    fun getAllChars() : Single<List<SeriesSearch>>
}