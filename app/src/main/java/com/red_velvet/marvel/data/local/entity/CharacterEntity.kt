package com.red_velvet.marvel.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CHARACTERS")
data class CharacterEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val modified: String,
    val imageUrl: String
)