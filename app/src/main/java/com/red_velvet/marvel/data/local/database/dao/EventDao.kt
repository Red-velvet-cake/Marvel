package com.red_velvet.marvel.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.red_velvet.marvel.data.local.database.entity.EventEntity
import io.reactivex.rxjava3.core.Completable

@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEvent(event: EventEntity): Completable
}