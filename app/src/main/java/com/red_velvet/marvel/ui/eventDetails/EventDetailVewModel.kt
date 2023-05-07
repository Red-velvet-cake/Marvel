package com.red_velvet.marvel.ui.eventDetails

import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.data.model.CharactersResponse
import com.red_velvet.marvel.data.model.CreatorsResponse
import com.red_velvet.marvel.data.model.EventsResponse
import com.red_velvet.marvel.data.remote.RetrofitClient
import com.red_velvet.marvel.data.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.ui.base.BaseViewModel
import com.red_velvet.marvel.ui.utils.State

class EventDetailVewModel : BaseViewModel(), CharactersInteractionListener,
    CreatorsInteractionListener {


    private val repository = MarvelRepositoryImpl(RetrofitClient.apiService)

    private var _event: MutableLiveData<State<List<EventsResponse>>> = MutableLiveData()
    val event: MutableLiveData<State<List<EventsResponse>>> = _event

    private var _characters: MutableLiveData<State<List<CharactersResponse>>> = MutableLiveData()
    val characters: MutableLiveData<State<List<CharactersResponse>>> = _characters

    private var _creators: MutableLiveData<State<List<CreatorsResponse>>> = MutableLiveData()
    val creators: MutableLiveData<State<List<CreatorsResponse>>> = _creators


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

    private fun onGetEventSuccess(state: State<List<EventsResponse>?>) {
        _event.postValue(State.Loading)
        state.toData()?.let {
            _event.postValue(State.Success(it))
        }
    }

    private fun onGetEventError(e: Throwable) {
        _event.postValue(State.Failed(e.message.toString()))
    }

    private fun onGetEventCreatorsSuccess(state: State<List<CreatorsResponse>?>) {
        _creators.postValue(State.Loading)
        state.toData()?.let {
            _creators.postValue(State.Success(it))
        }
    }

    private fun onGetEventCreatorsError(e: Throwable) {
        _creators.postValue(State.Failed(e.message.toString()))
    }

    private fun onGetEventCharactersSuccess(state: State<List<CharactersResponse>?>) {
        _characters.postValue(State.Loading)
        state.toData()?.let {
            _characters.postValue(State.Success(it))
        }
    }

    private fun onGetEventCharactersError(e: Throwable) {
        _characters.postValue(State.Failed(e.message.toString()))
    }


}