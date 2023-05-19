package com.red_velvet.marvel.data.repository

import com.red_velvet.marvel.data.entity.Comics
import io.reactivex.rxjava3.core.Completable

class MovieRepository  {
    val dao = ComicsDataBase.getInstanceWithoutContext().comicsDao()

    fun insertData(comics: Comics): Completable {
       return dao.insertComics(comics)
    }
    fun getComics()=dao.getAllComics()

}