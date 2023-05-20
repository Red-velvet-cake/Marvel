package com.red_velvet.marvel.domain.mapper

interface Mapper<I,O> {
    fun map(input : I): O
}