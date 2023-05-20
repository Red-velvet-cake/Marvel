package com.red_velvet.marvel.domain.mappers

import com.red_velvet.marvel.data.local.entity.CharacterEntity
import com.red_velvet.marvel.domain.models.Character
import javax.inject.Inject

class CharacterMapper @Inject constructor() : Mapper<CharacterEntity, Character> {
    override fun map(input: CharacterEntity): Character {
        return Character(
            id = input.id,
            name = input.title,
            imageUrl = input.imageUrl
        )
    }
}