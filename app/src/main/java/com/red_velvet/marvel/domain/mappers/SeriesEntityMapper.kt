package com.red_velvet.marvel.domain.mappers

import com.red_velvet.marvel.data.local.entity.SeriesEntity
import com.red_velvet.marvel.data.remote.dtos.SeriesDto
import javax.inject.Inject

class SeriesEntityMapper @Inject constructor() : Mapper<SeriesDto, SeriesEntity> {
    override fun map(input: SeriesDto): SeriesEntity {
        return SeriesEntity(
            id = input.id ?: 0,
            title = input.title ?: "",
            description = input.description ?: "",
            modified = input.modified ?: "",
            imageUrl = "${input.thumbnail?.path}.${input.thumbnail?.extension}"
        )
    }
}