package com.red_velvet.marvel.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.data.model.CharactersResponse
import com.red_velvet.marvel.data.model.ComicsResponse
import com.red_velvet.marvel.data.remote.RetrofitClient
import com.red_velvet.marvel.data.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.data.util.State
import com.red_velvet.marvel.ui.MarvelRepository
import com.red_velvet.marvel.ui.base.BaseViewModel

class ComicsViewModel : BaseViewModel() {

    private val _chars: MutableLiveData<State<List<CharactersResponse>>> = MutableLiveData()
    val chars: LiveData<State<List<CharactersResponse>>> = _chars

    private val _comics: MutableLiveData<State<List<ComicsResponse>>> = MutableLiveData()
    val comics: LiveData<State<List<ComicsResponse>>> get() = _comics

    private val _char: MutableLiveData<State<List<CharactersResponse>>> = MutableLiveData()
    val character: LiveData<State<List<CharactersResponse>>> get() = _char


    private val repository: MarvelRepository = MarvelRepositoryImpl(RetrofitClient.apiService)

    init {
//        getAllChars()
//        getCharById()
    }

//    fun getAllComics() {
//        getData(_comics, repository.getComics())
//    }
//
//    fun getAllChars() {
//        getData(_chars, repository.getCharacters())
//    }
//
//    fun getCharById() {
//        getData(_char, repository.getCharsByComicId(1749))
//    }
}