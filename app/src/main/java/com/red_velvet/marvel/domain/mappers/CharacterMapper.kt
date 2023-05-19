package com.red_velvet.marvel.domain.mappers

import com.red_velvet.marvel.data.local.entity.CharacterEntity
import com.red_velvet.marvel.domain.models.Charcter
import javax.inject.Inject

class CharacterMapper @Inject constructor() : Mapper<CharacterEntity, Charcter> {
    override fun map(input: CharacterEntity): Charcter {
        return Charcter(
            id = input.id,
            name = input.title,
            imageUrl = input.imageUrl
        )
    }
}