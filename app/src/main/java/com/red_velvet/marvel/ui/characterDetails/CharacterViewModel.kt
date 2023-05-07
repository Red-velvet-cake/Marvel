package com.red_velvet.marvel.ui.characterDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.data.model.CharactersResponse
import com.red_velvet.marvel.data.model.ComicsResponse
import com.red_velvet.marvel.data.model.SeriesResponse
import com.red_velvet.marvel.data.remote.RetrofitClient
import com.red_velvet.marvel.data.repository.MarvelRepository
import com.red_velvet.marvel.data.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.ui.base.BaseViewModel
import com.red_velvet.marvel.ui.utils.State

class CharacterViewModel : BaseViewModel(), SeriesInteractionListener, ComicsInteractionListener {

    private val _characterDetails: MutableLiveData<State<List<CharactersResponse>>> =
        MutableLiveData()
    val characterDetails: LiveData<State<List<CharactersResponse>>> get() = _characterDetails

    private val _comics: MutableLiveData<State<List<ComicsResponse>>> = MutableLiveData()
    val comics: LiveData<State<List<ComicsResponse>>> get() = _comics

    private val _series: MutableLiveData<State<List<SeriesResponse>>> = MutableLiveData()
    val series: LiveData<State<List<SeriesResponse>>> get() = _series

    private val repository: MarvelRepository = MarvelRepositoryImpl(RetrofitClient.apiService)

    fun getCharacterDetails(characterId: Int) {
        bindStateUpdates(
            repository.getCharacterByCharacterId(characterId),
            onNext = ::onGetCharacterDetailsSuccess,
            onError = ::onGetCharacterDetailsError
        )
    }

    private fun onGetCharacterDetailsSuccess(state: State<List<CharactersResponse>?>) {
        _characterDetails.postValue(State.Loading)
        state.toData()?.let {
            _characterDetails.postValue(State.Success(it))
        }
    }

    private fun onGetCharacterDetailsError(error: Throwable) {
        _characterDetails.postValue(State.Failed(error.message.toString()))
    }

    fun getComicsDyCharacterId(characterId: Int) {
        bindStateUpdates(
            repository.getComicsByCharacterId(characterId),
            onNext = ::onGetComicsDyCharacterIdSuccess,
            onError = ::onGetComicsDyCharacterIdError
        )
    }

    private fun onGetComicsDyCharacterIdSuccess(state: State<List<ComicsResponse>?>) {
        _comics.postValue(State.Loading)
        state.toData()?.let {
            _comics.postValue(State.Success(it))
        }
    }

    private fun onGetComicsDyCharacterIdError(error: Throwable) {
        _characterDetails.postValue(State.Failed(error.message.toString()))
    }

    fun getSeriesDyCharacterId(characterId: Int) {
        bindStateUpdates(
            repository.getSeriesByCharacterId(characterId),
            onNext = ::onGetSeriesDyCharacterIdSuccess,
            onError = ::onGetSeriesDyCharacterIdError
        )
    }

    private fun onGetSeriesDyCharacterIdSuccess(state: State<List<SeriesResponse>?>) {
        _series.postValue(State.Loading)
        state.toData()?.let {
            _series.postValue(State.Success(it))
        }
    }

    private fun onGetSeriesDyCharacterIdError(error: Throwable) {
        _series.postValue(State.Failed(error.message.toString()))
    }

}