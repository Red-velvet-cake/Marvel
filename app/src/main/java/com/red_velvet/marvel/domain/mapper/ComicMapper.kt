package com.red_velvet.marvel.domain.mapper

import com.red_velvet.marvel.data.remote.dto.ComicDto
import com.red_velvet.marvel.domain.model.Comic
import com.red_velvet.marvel.domain.util.toUrl

class ComicMapper : Mapper<ComicDto, Comic> {
    override fun map(input: ComicDto): Comic {
        return Comic(
            id = input.id ?: 0,
            title = input.title ?: "",
            imageUrl = input.thumbnail?.toUrl() ?: "",
        )
    }

}