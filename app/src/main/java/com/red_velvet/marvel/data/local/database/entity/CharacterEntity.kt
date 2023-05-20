package com.red_velvet.marvel.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CHARACTER_TABLE")
data class CharacterEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val imageUrl: String,
)
