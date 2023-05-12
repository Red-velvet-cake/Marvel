package com.red_velvet.marvel.ui.comicDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.data.model.Character
import com.red_velvet.marvel.data.model.Comic
import com.red_velvet.marvel.data.model.Creator
import com.red_velvet.marvel.data.remote.RetrofitClient
import com.red_velvet.marvel.data.repository.MarvelRepository
import com.red_velvet.marvel.data.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.ui.base.BaseViewModel
import com.red_velvet.marvel.ui.utils.SingleEvent
import com.red_velvet.marvel.ui.utils.State

class ComicDetailsViewModel : BaseViewModel(), ComicDetailsCreatorListenerInteraction,
    ComicDetailsCharacterListenerInteraction {

    private val _comicsDetails: MutableLiveData<State<List<Comic>>> = MutableLiveData()
    val comicsDetails: LiveData<State<List<Comic>>> = _comicsDetails

    private val _creators: MutableLiveData<State<List<Creator>>> = MutableLiveData()
    val creators: LiveData<State<List<Creator>>> = _creators

    private val _characters: MutableLiveData<State<List<Character>>> = MutableLiveData()
    val characters: LiveData<State<List<Character>>> = _characters

    private val repository: MarvelRepository by lazy {
        MarvelRepositoryImpl(RetrofitClient.apiService)
    }

    private val _navigationToCharacterDetails: MutableLiveData<SingleEvent<Int>> = MutableLiveData()
    val navigationToCharacterDetails: LiveData<SingleEvent<Int>> = _navigationToCharacterDetails

    fun loadComicDetails(comicId: Int) {
        fetchComicDetailsData(comicId)
        fetchCreatorsByComicIDData(comicId)
        fetchCharactersByComicIDData(comicId)
    }

    private fun fetchComicDetailsData(comicId: Int) {
        bindStateUpdates(
            repository.getComicDetail(comicId),
            onNext = ::onGetComicDetailsDataSuccess,
            onError = ::onGetComicDetailsDataError
        )
    }

    private fun fetchCreatorsByComicIDData(comicId: Int) {
        bindStateUpdates(
            repository.getComicCreatorByComicId(comicId),
            onNext = ::onGetCreatorsByComicIDDataSuccess,
            onError = ::onGetCreatorsByComicIDDataError
        )
    }


    private fun fetchCharactersByComicIDData(comicId: Int) {
        bindStateUpdates(
            repository.getCharsByComicId(comicId),
            onNext = ::onGetCharactersByComicIDDataSuccess,
            onError = ::onGetCharactersByComicIDDataError
        )
    }

    private fun onGetComicDetailsDataSuccess(state: State<List<Comic>>) {
        _comicsDetails.postValue(state)
    }

    private fun onGetComicDetailsDataError(e: Throwable) {
        _comicsDetails.postValue(State.Failed(e.message.toString()))
    }

    private fun onGetCreatorsByComicIDDataSuccess(state: State<List<Creator>>) {
        _creators.postValue(state)
    }

    private fun onGetCreatorsByComicIDDataError(e: Throwable) {
        _creators.postValue(State.Failed(e.message.toString()))
    }

    private fun onGetCharactersByComicIDDataSuccess(state: State<List<Character>>) {
        _characters.postValue(state)
    }

    private fun onGetCharactersByComicIDDataError(e: Throwable) {
        _characters.postValue(State.Failed(e.message.toString()))
    }

    override fun onClickCharacter(characterId: Int) {
        _navigationToCharacterDetails.postValue(SingleEvent(characterId))
    }

}