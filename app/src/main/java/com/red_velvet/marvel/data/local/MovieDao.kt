package com.red_velvet.marvel.data.local

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.red_velvet.marvel.data.entity.CharsEntity
import com.red_velvet.marvel.data.entity.ComicsEntity
import com.red_velvet.marvel.data.entity.EventsEntity
import com.red_velvet.marvel.data.entity.EventsSearch
import com.red_velvet.marvel.data.entity.SeriesSearch
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertChars(charsEntity: List<CharsEntity>): Completable

    @Query("SELECT * FROM CHARS_TABLE")
    fun getAllChars() : Observable<List<CharsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComics(comicsEntity: List<ComicsEntity>):Completable

    @Query("SELECT * FROM COMICS_TABLE")
    fun getAllComics() : Observable<List<ComicsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEvents(eventsEntity: List<EventsEntity>): Completable

    @Query("SELECT * FROM EVENTS_TABLE")
    fun getAllEvents() : Observable<List<EventsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEventsSearch(eventsSearch: EventsSearch): Completable

    @Query("SELECT * FROM EVENTS_SEARCH_TABLE")
    fun getAllEventsSearch() : Observable<List<EventsSearch>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSeries(seriesSearch: SeriesSearch): Completable

    @Query("SELECT * FROM SERIES_SEARCH_TABLE")
    fun getAllSeries() : Observable<List<SeriesSearch>>
}