package com.red_velvet.marvel.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.red_velvet.marvel.data.entity.Events
import com.red_velvet.marvel.data.entity.EventsSearch
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface EventsDao {
    @Insert
    fun insertEvents(events: Events): Completable

    @Query("SELECT * FROM EVENTS_TABLE")
    fun getAllEvents() : Single<List<Events>>

    @Insert
    fun insertEventsSearch(eventsSearch: EventsSearch): Completable

    @Query("SELECT * FROM EVENTS_SEARCH_TABLE")
    fun getAllEventsSearch() : Single<List<EventsSearch>>
}