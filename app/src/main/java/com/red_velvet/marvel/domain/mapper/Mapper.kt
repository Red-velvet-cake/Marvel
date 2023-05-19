package com.red_velvet.marvel.domain.mapper

interface Mapper<in I, out O> {
    fun map(input: I): O
}