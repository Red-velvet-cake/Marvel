package com.red_velvet.marvel.domain.model

data class Comic(
    val id: Int,
    val title: String,
    val description: String,
    val thumbnail: Thumbnail,
)
