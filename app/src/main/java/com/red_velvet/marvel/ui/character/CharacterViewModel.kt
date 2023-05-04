package com.red_velvet.marvel.ui.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.data.model.CharactersResponse
import com.red_velvet.marvel.data.model.ComicsResponse
import com.red_velvet.marvel.data.model.SeriesResponse
import com.red_velvet.marvel.data.remote.RetrofitClient
import com.red_velvet.marvel.data.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.data.util.State
import com.red_velvet.marvel.ui.MarvelRepository
import com.red_velvet.marvel.ui.base.BaseViewModel

class CharacterViewModel  : BaseViewModel() , SeriesInteractionListener ,ComicsInteractionListener {

    private val _characterDetails: MutableLiveData<State<List<CharactersResponse>>> =
        MutableLiveData()
    val characterDetails: LiveData<State<List<CharactersResponse>>> get() = _characterDetails

    private val _comics: MutableLiveData<State<List<ComicsResponse>>> = MutableLiveData()
    val comics: LiveData<State<List<ComicsResponse>>> get() = _comics

    private val _series: MutableLiveData<State<List<SeriesResponse>>> = MutableLiveData()
    val series: LiveData<State<List<SeriesResponse>>> get() = _series

    private val repository: MarvelRepository = MarvelRepositoryImpl(RetrofitClient.apiService)

    fun getCharacterDetails(characterId: Int) {
        getData(_characterDetails, repository.getCharacterByCharacterId(characterId))
    }

    fun getComicsDyCharacterId(characterId: Int) {
        getData(_comics, repository.getComicsByCharacterId(characterId))
    }

    fun getSeriesDyCharacterId(characterId: Int) {
        getData(_series, repository.getSeriesByCharacterId(characterId))
    }


}