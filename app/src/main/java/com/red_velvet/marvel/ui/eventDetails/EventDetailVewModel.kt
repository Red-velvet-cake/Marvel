package com.red_velvet.marvel.ui.eventDetails

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
import java.lang.Exception


class EventDetailVewModel: BaseViewModel() {
    private val repository=MarvelRepositoryImpl(RetrofitClient.apiService)
    private var _event=MutableLiveData<State<EventsResponse>>()
    val event:MutableLiveData<State<EventsResponse>>
        get()=_event

    init {
        getEvent()
    }
    private fun getEvent(){
        _event.postValue(State.Loading)
        try {
            repository.getEventDetails(1)
                .doOnError (
                    ::onError
                )
                .doOnSuccess (
                    ::onSuccess
                ).subscribeBy(
                    onError = ::onError,
                    onSuccess=::onSuccess
                )
                .addTo(compositeDisposable)

        }catch (e:Exception){
            onError(e)
        }
    }
    private fun onError(error: Throwable){
        _event.postValue(State.Failed(error.message.toString()))
    }
    private fun onSuccess(response: BaseResponse<EventsResponse>){
        _event.postValue(State.Success(response.data?.results!!.))
    }

}