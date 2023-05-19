package com.red_velvet.marvel.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "EVENT_TABLE")
data class EventEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val imageUrl: String,
)
