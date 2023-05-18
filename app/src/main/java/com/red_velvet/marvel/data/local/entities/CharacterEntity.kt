package com.red_velvet.marvel.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CharacterEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val description: String,
    val imageUrl: String,
)