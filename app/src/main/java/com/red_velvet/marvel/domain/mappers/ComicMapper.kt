package com.red_velvet.marvel.domain.mappers

import com.red_velvet.marvel.data.local.entity.ComicEntity
import com.red_velvet.marvel.domain.models.Comic
import javax.inject.Inject

class ComicMapper @Inject constructor() : Mapper<ComicEntity, Comic> {
    override fun map(input: ComicEntity): Comic {
        return Comic(
            id = input.id,
            name = input.title,
            imageUrl = input.imageUrl
        )
    }
}