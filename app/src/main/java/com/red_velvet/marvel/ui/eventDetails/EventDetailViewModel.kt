package com.red_velvet.marvel.ui.eventDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.data.model.Character
import com.red_velvet.marvel.data.model.Creator
import com.red_velvet.marvel.data.model.Event
import com.red_velvet.marvel.data.remote.RetrofitClient
import com.red_velvet.marvel.data.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.ui.base.BaseViewModel
import com.red_velvet.marvel.ui.utils.SingleEvent
import com.red_velvet.marvel.ui.utils.State

class EventDetailViewModel : BaseViewModel(), CharactersInteractionListener,
    CreatorsInteractionListener {

    private val repository = MarvelRepositoryImpl(RetrofitClient.apiService)

    private var _event: MutableLiveData<State<List<Event>>> = MutableLiveData()
    val event: MutableLiveData<State<List<Event>>> = _event

    private var _characters: MutableLiveData<State<List<Character>>> = MutableLiveData()
    val characters: MutableLiveData<State<List<Character>>> = _characters

    private var _creators: MutableLiveData<State<List<Creator>>> = MutableLiveData()
    val creators: MutableLiveData<State<List<Creator>>> = _creators

    private val _navigationToCharacterDetails: MutableLiveData<SingleEvent<Int>> = MutableLiveData()
    val navigationToCharacterDetails: LiveData<SingleEvent<Int>> = _navigationToCharacterDetails

    fun getEvent(eventId: Int) {
        bindStateUpdates(
            repository.getEventDetails(eventId),
            onNext = ::onGetEventSuccess,
            onError = ::onGetEventError
        )
    }

    fun getCreatorsEventId(eventId: Int) {
        bindStateUpdates(
            repository.getCreatorsByEventId(eventId),
            onNext = ::onGetEventCreatorsSuccess,
            onError = ::onGetEventCreatorsError
        )
    }

    fun getCharactersEventId(eventId: Int) {
        bindStateUpdates(
            repository.getCharactersByEventId(eventId),
            onNext = ::onGetEventCharactersSuccess,
            onError = ::onGetEventCharactersError
        )
    }

    private fun onGetEventSuccess(state: State<List<Event>>) {
        _event.postValue(state)
    }

    private fun onGetEventError(e: Throwable) {
        _event.postValue(State.Failed(e.message.toString()))
    }

    private fun onGetEventCreatorsSuccess(state: State<List<Creator>>) {
        _creators.postValue(state)
    }

    private fun onGetEventCreatorsError(e: Throwable) {
        _creators.postValue(State.Failed(e.message.toString()))
    }

    private fun onGetEventCharactersSuccess(state: State<List<Character>>) {
        _characters.postValue(state)
    }

    private fun onGetEventCharactersError(e: Throwable) {
        _characters.postValue(State.Failed(e.message.toString()))
    }

    override fun onCharacterClicked(characterId: Int) {
        _navigationToCharacterDetails.postValue(SingleEvent(characterId))
    }

    fun onTryAgainClicked() {
        TODO("You need ID inside the viewModel")
        getEvent(404)
        getCharactersEventId(404)
        getCreatorsEventId(404)
    }

}