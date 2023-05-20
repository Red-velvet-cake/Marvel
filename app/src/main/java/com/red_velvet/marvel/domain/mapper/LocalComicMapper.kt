package com.red_velvet.marvel.domain.mapper

import com.red_velvet.marvel.data.local.database.entity.ComicEntity
import com.red_velvet.marvel.domain.model.Comic

class LocalComicMapper : Mapper<ComicEntity, Comic> {
    override fun map(input: ComicEntity): Comic {
        return Comic(
            id = input.id,
            title = input.title,
            imageUrl = input.imageUrl,
        )
    }
}