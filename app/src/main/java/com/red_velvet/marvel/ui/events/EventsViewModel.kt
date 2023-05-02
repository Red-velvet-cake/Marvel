package com.red_velvet.marvel.ui.events

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.data.model.BaseResponse
import com.red_velvet.marvel.data.model.EventsResponse
import com.red_velvet.marvel.data.remote.RetrofitClient
import com.red_velvet.marvel.data.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.data.util.State
import com.red_velvet.marvel.ui.base.BaseViewModel
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy

class EventsViewModel : BaseViewModel() {

    private val repository = MarvelRepositoryImpl(RetrofitClient.apiService)
    private val _event = MutableLiveData<State<List<EventsResponse>>>()
    val event: LiveData<State<List<EventsResponse>>>
        get() = _event

    init {
        getEvents()
    }

    private fun getEvents() {

        _event.postValue(State.Loading)

        val disposable = repository.getEvents()
            .doOnError {
                onGetEventsError(it)
            }
            .doOnSuccess {
                onGetEventsSuccess(it)
            }
            .subscribeBy(
                onSuccess = ::onGetEventsSuccess,
                onError = ::onGetEventsError
            )

        disposable.addTo(compositeDisposable)

    }

    private fun onGetEventsError(error: Throwable) {
        _event.postValue(State.Failed(error.message.toString()))
    }

    private fun onGetEventsSuccess(response: BaseResponse<EventsResponse>) {
        _event.postValue(State.Success(response.data?.results!!))
    }

}