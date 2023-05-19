package com.red_velvet.marvel.domain.mappers

import com.red_velvet.marvel.data.model.CharacterDto
import com.red_velvet.marvel.data.model.ComicDto
import com.red_velvet.marvel.domain.models.Chars
import com.red_velvet.marvel.domain.models.Comic
import javax.inject.Inject

class CharsMapper @Inject constructor(): Mapper<CharacterDto, Chars> {
    override fun map(input: CharacterDto): Chars {
        return  Chars(
            id =input.id, title = input.name,
            image = "${input.thumbnail?.path}.${input.thumbnail?.extension}" )
    }
}