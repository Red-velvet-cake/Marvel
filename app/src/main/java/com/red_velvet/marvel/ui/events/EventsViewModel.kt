package com.red_velvet.marvel.ui.events

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.data.entity.EventsSearch
import com.red_velvet.marvel.data.model.EventDto
import com.red_velvet.marvel.data.remote.RetrofitClient
import com.red_velvet.marvel.data.repository.MarvelRepository
import com.red_velvet.marvel.data.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.ui.base.BaseViewModel
import com.red_velvet.marvel.ui.utils.SingleEvent
import com.red_velvet.marvel.ui.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject


@HiltViewModel
class EventsViewModel @Inject constructor (private val repository: MarvelRepository)  : BaseViewModel(), EventsInteractionListener {


    private val _events = MutableLiveData<State<List<EventDto>>>()
    val events: LiveData<State<List<EventDto>>> = _events

    private val _navigationToEventDetails = MutableLiveData<SingleEvent<Int>>()
    val navigationToEventDetails: LiveData<SingleEvent<Int>> = _navigationToEventDetails

    val searchQuery = MutableLiveData<String>()

    private val _eventsSearch = MutableLiveData<List<EventsSearch>>()
    val eventsSearch: LiveData<List<EventsSearch>> = _eventsSearch

    init {
        getAllEvents()
        initSearchObservable()
    }
    fun getHistorySearch(){

        repository.getEventsSearch().subscribeOn(Schedulers.io()) .observeOn(
            AndroidSchedulers
                .mainThread()
        )
            .subscribe(
::onGetHistory
            )
    }
    fun onGetHistory(eventsSearch:List<EventsSearch>){
        _eventsSearch.postValue(eventsSearch)
    }


    fun getAllEvents(query: String? = null) {
        bindStateUpdates(
            repository.getAllEvents(query),
            onError = ::onGetAllEventsFailure,
            onNext = ::onGetAllEventsState
        )
    }

    private fun onGetAllEventsState(state: State<List<EventDto>>) {
        _events.postValue(state)
    }

    private fun onGetAllEventsFailure(e: Throwable) {
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
                    repository.getCashedEvents()
                        .switchIfEmpty(getAllEvents(query))
                     getAllEvents(query) ?:getHistorySearch()
                }
            }.addTo(compositeDisposable)
    }

    override fun doOnEventClicked(eventId: Int) {
        _navigationToEventDetails.postValue(SingleEvent(eventId))
    }

}