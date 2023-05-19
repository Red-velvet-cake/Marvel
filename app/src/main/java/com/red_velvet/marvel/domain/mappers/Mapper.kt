package com.red_velvet.marvel.domain.mappers

interface Mapper<I,O> {

    fun map(input:I):O
}