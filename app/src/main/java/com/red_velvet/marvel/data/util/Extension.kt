package com.red_velvet.marvel.data.util

import com.red_velvet.marvel.data.remote.dto.BaseResponse
import retrofit2.Response

fun <T> Response<BaseResponse<List<T>>>.extractResults(): List<T>? {
    return this.body()?.body?.results
}