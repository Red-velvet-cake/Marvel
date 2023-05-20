package com.red_velvet.marvel.domain.mapper

import com.red_velvet.marvel.data.local.entity.ComicEntity
import com.red_velvet.marvel.domain.model.Comic

class ComicMapper : Mapper<ComicEntity , Comic> {
    override fun map(input: ComicEntity): Comic {
        return Comic(
            id = input.id ?: 0L ,
            name = input.name ?: "" ,
            imageUrl = input.imageUrl ?: ""
        )
    }

}