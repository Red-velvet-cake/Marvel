package com.red_velvet.marvel.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "EVENTS")
data class EventEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val modified: String,
    val imageUrl: String
)
