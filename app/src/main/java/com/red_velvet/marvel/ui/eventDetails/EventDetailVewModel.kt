package com.red_velvet.marvel.ui.eventDetails

import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.data.model.CharactersResponse
import com.red_velvet.marvel.data.model.CreatorsResponse
import com.red_velvet.marvel.data.model.EventsResponse
import com.red_velvet.marvel.data.model.MarvelResponse
import com.red_velvet.marvel.data.remote.RetrofitClient
import com.red_velvet.marvel.data.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.data.util.State
import com.red_velvet.marvel.ui.base.BaseViewModel

class EventDetailVewModel: BaseViewModel() {
    
    private  val eventId=116
    private val repository=MarvelRepositoryImpl(RetrofitClient.apiService)
    private var _event:MutableLiveData<State<List<EventsResponse>>> = MutableLiveData()
    val event:MutableLiveData<State<List<EventsResponse>>>
        get()=_event
    private var _characters:MutableLiveData<State<List<CharactersResponse>>> =   MutableLiveData()
    val characters:MutableLiveData<State<List<CharactersResponse>>>
        get()= _characters
    private var _creators:MutableLiveData<State<List<CreatorsResponse>>>  = MutableLiveData()
    val creators:MutableLiveData<State<List<CreatorsResponse>>>
        get()=_creators

    init {
        getEvent()
    }
     fun getEvent() {
//         bindStateUpdates(repository.getEventDetails(eventId),::onError,::onSuccess)
//        getData(_event,repository.getEventDetails(eventId))
    }
//        private fun onSuccess(state: State) {
//
//    }
   private fun onError(throwable: Throwable){

    }
     fun getCreatorsEventId() {
//        getData(_creators,repository.getCreatorsByEventId(eventId))
    }
     fun getCharactersEventId() {
//        getData(_characters,repository.getCharactersByEventId(eventId))
    }
}