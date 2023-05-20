package com.red_velvet.marvel.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.red_velvet.marvel.data.local.database.entity.EventEntity
import io.reactivex.rxjava3.core.Observable

@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(event: EventEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(events: List<EventEntity>)

    @Query("SELECT * FROM EVENT_TABLE")
    fun getAll(): Observable<List<EventEntity>>
}