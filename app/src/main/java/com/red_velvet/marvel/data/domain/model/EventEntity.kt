package com.red_velvet.marvel.data.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EventEntity(
    @PrimaryKey var id: Long,
    var title: String,
    var description: String,
    var imageUrl: String,
)