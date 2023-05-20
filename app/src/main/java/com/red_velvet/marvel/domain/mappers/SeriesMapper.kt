package com.red_velvet.marvel.domain.mappers

import com.red_velvet.marvel.data.local.entity.SeriesEntity
import com.red_velvet.marvel.domain.models.Series
import javax.inject.Inject

class SeriesMapper @Inject constructor() : Mapper<SeriesEntity, Series> {
    override fun map(input: SeriesEntity): Series {
        return Series(
            id = input.id,
            name = input.title,
            imageUrl = input.imageUrl
        )
    }
}