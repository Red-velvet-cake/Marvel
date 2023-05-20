package com.red_velvet.marvel.domain.mappers

import com.red_velvet.marvel.data.entity.ComicsEntity
import com.red_velvet.marvel.data.model.ComicDto
import com.red_velvet.marvel.domain.models.Comic
import javax.inject.Inject

class ComicsMapper  @Inject constructor() : Mapper<ComicsEntity, Comic> {
    override fun map(input: ComicsEntity): Comic {
       return  Comic(
           id =input.id, title = input.title,
           image = input.img )
    }
}
