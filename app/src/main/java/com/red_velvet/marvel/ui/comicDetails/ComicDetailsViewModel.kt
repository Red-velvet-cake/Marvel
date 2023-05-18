package com.red_velvet.marvel.ui.comicDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.data.remote.RetrofitClient
import com.red_velvet.marvel.data.remote.dto.CharacterDto
import com.red_velvet.marvel.data.remote.dto.ComicDto
import com.red_velvet.marvel.data.remote.dto.CreatorDto
import com.red_velvet.marvel.data.repository.MarvelRepository
import com.red_velvet.marvel.data.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.ui.base.BaseViewModel
import com.red_velvet.marvel.ui.utils.SingleEvent
import com.red_velvet.marvel.ui.utils.State

class ComicDetailsViewModel : BaseViewModel(), ComicDetailsCreatorListenerInteraction,
    ComicDetailsCharacterListenerInteraction {

    private val _comicsDetails: MutableLiveData<State<List<ComicDto>>> = MutableLiveData()
    val comicsDetails: LiveData<State<List<ComicDto>>> = _comicsDetails

    private val _creators: MutableLiveData<State<List<CreatorDto>>> = MutableLiveData()
    val creators: LiveData<State<List<CreatorDto>>> = _creators

    private val _characters: MutableLiveData<State<List<CharacterDto>>> = MutableLiveData()
    val characters: LiveData<State<List<CharacterDto>>> = _characters

    private val repository: MarvelRepository by lazy {
        MarvelRepositoryImpl(RetrofitClient.apiService)
    }

    private val _navigationToCharacterDetails: MutableLiveData<SingleEvent<Int>> = MutableLiveData()
    val navigationToCharacterDetails: LiveData<SingleEvent<Int>> = _navigationToCharacterDetails

    fun loadComicDetails(comicId: Int) {
        getComicById(comicId)
        getCreatorsByComicId(comicId)
        getCharactersByComicId(comicId)
    }

    private fun getComicById(comicId: Int) {
        bindStateUpdates(
            repository.getComicById(comicId),
            onNext = ::onGetComicState,
            onError = ::onGetComicError
        )
    }

    private fun getCreatorsByComicId(comicId: Int) {
        bindStateUpdates(
            repository.getCreatorByComicId(comicId),
            onNext = ::onGetCreatorsByComicIdState,
            onError = ::onGetCreatorsByComicIdError
        )
    }


    private fun getCharactersByComicId(comicId: Int) {
        bindStateUpdates(
            repository.getCharactersByComicId(comicId),
            onNext = ::onGetCharactersByComicIdState,
            onError = ::onGetCharactersByComicIdError
        )
    }

    private fun onGetComicState(state: State<List<ComicDto>>) {
        _comicsDetails.postValue(state)
    }

    private fun onGetComicError(e: Throwable) {
        _comicsDetails.postValue(State.Failed(e.message.toString()))
    }

    private fun onGetCreatorsByComicIdState(state: State<List<CreatorDto>>) {
        _creators.postValue(state)
    }

    private fun onGetCreatorsByComicIdError(e: Throwable) {
        _creators.postValue(State.Failed(e.message.toString()))
    }

    private fun onGetCharactersByComicIdState(state: State<List<CharacterDto>>) {
        _characters.postValue(state)
    }

    private fun onGetCharactersByComicIdError(e: Throwable) {
        _characters.postValue(State.Failed(e.message.toString()))
    }

    override fun doOnCharacterClicked(characterId: Int) {
        _navigationToCharacterDetails.postValue(SingleEvent(characterId))
    }
}