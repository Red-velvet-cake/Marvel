package com.red_velvet.marvel.domain.mapper

import com.red_velvet.marvel.data.remote.dto.CharacterDto
import com.red_velvet.marvel.domain.model.Character
import com.red_velvet.marvel.domain.util.toUrl

class CharacterMapper : Mapper<CharacterDto, Character> {
    override fun map(input: CharacterDto): Character {
        return Character(
            id = input.id ?: 0,
            name = input.name ?: "",
            imageUrl = input.thumbnail?.toUrl() ?: ""
        )
    }
}