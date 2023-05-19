package com.red_velvet.marvel.ui.characterDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.data.model.Character
import com.red_velvet.marvel.data.model.Comic
import com.red_velvet.marvel.data.model.Series
import com.red_velvet.marvel.data.remote.RetrofitClient
import com.red_velvet.marvel.data.domain.repository.MarvelRepository
import com.red_velvet.marvel.data.domain.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.ui.base.BaseViewModel
import com.red_velvet.marvel.ui.utils.SingleEvent
import com.red_velvet.marvel.ui.utils.State

class CharacterDetailsViewModel : BaseViewModel(), SeriesInteractionListener,
    ComicsInteractionListener {

    private val repository: MarvelRepository by lazy {
        MarvelRepositoryImpl(RetrofitClient.apiService)
    }

    private val _characterDetails: MutableLiveData<State<List<Character>>> = MutableLiveData()
    val characterDetails: LiveData<State<List<Character>>> = _characterDetails

    private val _comics: MutableLiveData<State<List<Comic>>> = MutableLiveData()
    val comics: LiveData<State<List<Comic>>> = _comics

    private val _series: MutableLiveData<State<List<Series>>> = MutableLiveData()
    val series: LiveData<State<List<Series>>> = _series

    private val _navigationToComicDetails: MutableLiveData<SingleEvent<Int>> = MutableLiveData()
    val navigationToComicDetails: LiveData<SingleEvent<Int>> = _navigationToComicDetails

    private val _navigationToSeriesDetails: MutableLiveData<SingleEvent<Int>> = MutableLiveData()
    val navigationToSeriesDetails: LiveData<SingleEvent<Int>> = _navigationToSeriesDetails

    fun loadCharacterDetails(characterId: Int) {
        getCharacterById(characterId)
        getComicsDyCharacterId(characterId)
        getSeriesByCharacterId(characterId)
    }

    private fun getCharacterById(characterId: Int) {
        bindStateUpdates(
            repository.getCharacterById(characterId),
            onNext = ::onGetCharacterState,
            onError = ::onGetCharacterStateError
        )
    }

    private fun onGetCharacterState(state: State<List<Character>>) {
        _characterDetails.postValue(state)
    }

    private fun onGetCharacterStateError(error: Throwable) {
        _characterDetails.postValue(State.Failed(error.message.toString()))
    }

    private fun getComicsDyCharacterId(characterId: Int) {
        bindStateUpdates(
            repository.getComicsByCharacterId(characterId),
            onNext = ::onGetComicsByCharacterIdState,
            onError = ::onGetComicsByCharacterIdStateError
        )
    }

    private fun onGetComicsByCharacterIdState(state: State<List<Comic>>) {
        _comics.postValue(state)
    }

    private fun onGetComicsByCharacterIdStateError(error: Throwable) {
        _characterDetails.postValue(State.Failed(error.message.toString()))
    }

    private fun getSeriesByCharacterId(characterId: Int) {
        bindStateUpdates(
            repository.getSeriesByCharacterId(characterId),
            onNext = ::onGetSeriesByCharacterIdState,
            onError = ::onGetSeriesByCharacterIdError
        )
    }

    private fun onGetSeriesByCharacterIdState(state: State<List<Series>>) {
        _series.postValue(state)
    }

    private fun onGetSeriesByCharacterIdError(error: Throwable) {
        _series.postValue(State.Failed(error.message.toString()))
    }

    override fun doOnComicClicked(comicId: Int) {
        _navigationToComicDetails.postValue(SingleEvent(comicId))
    }

    override fun doOnSeriesClicked(seriesId: Int) {
        _navigationToSeriesDetails.postValue(SingleEvent(seriesId))
    }

}