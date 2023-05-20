package com.red_velvet.marvel.domain.mappers

import com.red_velvet.marvel.data.entity.CharsEntity
import com.red_velvet.marvel.data.model.CharacterDto
import com.red_velvet.marvel.data.model.ComicDto
import com.red_velvet.marvel.domain.models.Chars
import com.red_velvet.marvel.domain.models.Comic
import javax.inject.Inject

class CharsMapper @Inject constructor(): Mapper<CharsEntity, Chars> {
    override fun map(input: CharsEntity): Chars {
        return  Chars(
            id =input.id, title = input.title,
            image =input.img )
    }
}