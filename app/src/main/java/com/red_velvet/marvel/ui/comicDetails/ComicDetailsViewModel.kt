package com.red_velvet.marvel.ui.comicDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.data.model.Character
import com.red_velvet.marvel.data.model.ComicDto
import com.red_velvet.marvel.data.model.Creator
import com.red_velvet.marvel.domain.repository.MarvelRepository
import com.red_velvet.marvel.ui.base.BaseViewModel
import com.red_velvet.marvel.ui.utils.SingleEvent
import com.red_velvet.marvel.ui.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class ComicDetailsViewModel @Inject constructor(
    private val repository: MarvelRepository
) : BaseViewModel(), ComicDetailsCreatorListenerInteraction,
    ComicDetailsCharacterListenerInteraction {

    private val _comicsDetails: MutableLiveData<State<List<ComicDto>>> = MutableLiveData()
    val comicsDetails: LiveData<State<List<ComicDto>>> = _comicsDetails

    private val _creators: MutableLiveData<State<List<Creator>>> = MutableLiveData()
    val creators: LiveData<State<List<Creator>>> = _creators

    private val _characters: MutableLiveData<State<List<Character>>> = MutableLiveData()
    val characters: LiveData<State<List<Character>>> = _characters

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

    private fun onGetCreatorsByComicIdState(state: State<List<Creator>>) {
        _creators.postValue(state)
    }

    private fun onGetCreatorsByComicIdError(e: Throwable) {
        _creators.postValue(State.Failed(e.message.toString()))
    }

    private fun onGetCharactersByComicIdState(state: State<List<Character>>) {
        _characters.postValue(state)
    }

    private fun onGetCharactersByComicIdError(e: Throwable) {
        _characters.postValue(State.Failed(e.message.toString()))
    }

    override fun doOnCharacterClicked(characterId: Int) {
        _navigationToCharacterDetails.postValue(SingleEvent(characterId))
    }
}