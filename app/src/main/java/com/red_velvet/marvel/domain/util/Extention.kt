package com.red_velvet.marvel.domain.util

import com.red_velvet.marvel.data.remote.dto.ThumbnailDto

fun ThumbnailDto.toUrl() = this.path + "." + this.extension
