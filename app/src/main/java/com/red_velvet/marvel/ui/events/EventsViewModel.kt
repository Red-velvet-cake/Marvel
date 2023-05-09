package com.red_velvet.marvel.ui.events

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.data.model.Event
import com.red_velvet.marvel.data.remote.RetrofitClient
import com.red_velvet.marvel.data.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.ui.base.BaseViewModel
import com.red_velvet.marvel.ui.utils.SingleEvent
import com.red_velvet.marvel.ui.utils.State
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.addTo
import java.util.concurrent.TimeUnit

class EventsViewModel : BaseViewModel(), EventsInteractionListener {

    private val repository = MarvelRepositoryImpl(RetrofitClient.apiService)

    private val _events = MutableLiveData<State<List<Event>>>()
    val events: LiveData<State<List<Event>>> = _events

    private val _navigationToEventDetails = MutableLiveData<SingleEvent<Int>>()
    val navigationToEventDetails: LiveData<SingleEvent<Int>> = _navigationToEventDetails

    val searchQuery = MutableLiveData<String>()

    init {
        getAllEvents()
        initSearchObservable()
    }

    private fun getAllEvents() {
        bindStateUpdates(
            repository.getEvents(),
            ::onGetAllEventsError,
            ::onGetAllEventsSuccess
        )
    }

    private fun searchEvents(query: String? = null) {
        bindStateUpdates(
            repository.getEvents(query),
            ::onGetAllEventsError,
            ::onGetAllEventsSuccess
        )
    }

    private fun onGetAllEventsSuccess(state: State<List<Event>>) {
        _events.postValue(state)
    }

    private fun onGetAllEventsError(e: Throwable) {
        _events.postValue(State.Loading)
        _events.postValue(State.Failed(e.message.toString()))
    }

    private fun initSearchObservable() {
        Observable.create { emitter ->
            searchQuery.observeForever { query ->
                emitter.onNext(query)
            }
        }.debounce(300, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .subscribe { query ->
                if (query.isEmpty()) {
                    getAllEvents()
                } else {
                    searchEvents(query)
                }
            }.addTo(compositeDisposable)
    }

    override fun onEventClicked(eventId: Int) {
        _navigationToEventDetails.postValue(SingleEvent(eventId))
    }

    fun onTryAgainClicked() {
        getAllEvents()
        initSearchObservable()
    }

}