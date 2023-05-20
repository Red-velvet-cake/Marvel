package com.red_velvet.marvel.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EventEntity(
    @PrimaryKey
    val id : Long?,
    val name : String?,
    val description: String?,
    val modified : String?,
    val imageUrl : String?
)
