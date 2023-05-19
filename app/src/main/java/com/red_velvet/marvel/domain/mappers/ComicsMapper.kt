package com.red_velvet.marvel.domain.mappers

import com.red_velvet.marvel.data.model.ComicDto
import com.red_velvet.marvel.domain.models.Comic
import javax.inject.Inject

class ComicsMapper  @Inject constructor() : Mapper<ComicDto, Comic> {
    override fun map(input: ComicDto): Comic {
       return  Comic(
           id =input.id, title = input.title,
           image = "${input.thumbnail?.path}.${input.thumbnail?.extension}" )
    }
}
