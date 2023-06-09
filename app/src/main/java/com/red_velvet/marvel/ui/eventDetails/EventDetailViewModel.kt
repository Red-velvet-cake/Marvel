package com.red_velvet.marvel.ui.eventDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.data.model.Creator
import com.red_velvet.marvel.data.remote.dtos.CharacterDto
import com.red_velvet.marvel.data.remote.dtos.EventDto
import com.red_velvet.marvel.domain.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.ui.base.BaseViewModel
import com.red_velvet.marvel.ui.utils.SingleEvent
import com.red_velvet.marvel.ui.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EventDetailViewModel @Inject constructor(private val repository: MarvelRepositoryImpl) :
    BaseViewModel(), CharactersInteractionListener,
    CreatorsInteractionListener {

    private var _eventDto: MutableLiveData<State<List<EventDto>>> = MutableLiveData()
    val eventDto: MutableLiveData<State<List<EventDto>>> = _eventDto

    private var _characters: MutableLiveData<State<List<CharacterDto>>> = MutableLiveData()
    val characters: MutableLiveData<State<List<CharacterDto>>> = _characters

    private var _creators: MutableLiveData<State<List<Creator>>> = MutableLiveData()
    val creators: MutableLiveData<State<List<Creator>>> = _creators

    private val _navigationToCharacterDetails: MutableLiveData<SingleEvent<Int>> = MutableLiveData()
    val navigationToCharacterDetails: LiveData<SingleEvent<Int>> = _navigationToCharacterDetails

    fun loadEventDetails(eventId: Int) {
        getEventById(eventId)
        getCharactersByEventId(eventId)
        getCreatorsByEventId(eventId)
    }

    private fun getEventById(eventId: Int) {
        bindStateUpdates(
            repository.getEventById(eventId),
            onNext = ::onGetEventState,
            onError = ::onGetEventFailure
        )
    }

    private fun getCreatorsByEventId(eventId: Int) {
        bindStateUpdates(
            repository.getCreatorsByEventId(eventId),
            onNext = ::onGetEventCreatorsState,
            onError = ::onGetEventCreatorsFailure
        )
    }

    private fun getCharactersByEventId(eventId: Int) {
        bindStateUpdates(
            repository.getCharactersByEventId(eventId),
            onNext = ::onGetEventCharactersState,
            onError = ::onGetEventCharactersFailure
        )
    }

    private fun onGetEventState(state: State<List<EventDto>>) {
        _eventDto.postValue(state)
    }

    private fun onGetEventFailure(e: Throwable) {
        _eventDto.postValue(State.Failed(e.message.toString()))
    }

    private fun onGetEventCreatorsState(state: State<List<Creator>>) {
        _creators.postValue(state)
    }

    private fun onGetEventCreatorsFailure(e: Throwable) {
        _creators.postValue(State.Failed(e.message.toString()))
    }

    private fun onGetEventCharactersState(state: State<List<CharacterDto>>) {
        _characters.postValue(state)
    }

    private fun onGetEventCharactersFailure(e: Throwable) {
        _characters.postValue(State.Failed(e.message.toString()))
    }

    override fun doOnCharacterClicked(characterId: Int) {
        _navigationToCharacterDetails.postValue(SingleEvent(characterId))
    }

}