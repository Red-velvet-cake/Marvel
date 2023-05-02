package com.red_velvet.marvel.ui.base

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.red_velvet.marvel.data.util.State
import com.red_velvet.marvel.data.util.applySchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy

abstract class BaseViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun <T> getData(liveData: MutableLiveData<State<T>>, stateObservable: Observable<State<T?>>) {
        try {
            stateObservable
                .applySchedulers()
                .doOnError { onGetDataError(liveData, it) }
                .doOnNext { handleUIState(liveData, it) }
                .subscribeBy(
                    onError = { onGetDataError(liveData, it) },
                    onNext = { handleUIState(liveData, it) },
                )
                .addTo(compositeDisposable)
        } catch (e: Exception) {
            onGetDataError(liveData, e)
        }
    }

    private fun <T> onGetDataError(data: MutableLiveData<State<T>>, error: Throwable) {
        Log.d("SADEQMHANA", "onGetComicsError: ${error.message}")
        data.postValue(State.Failed(error.message.toString()))
    }

    private fun <T> handleUIState(data: MutableLiveData<State<T>>, state: State<T?>) {
        when (state) {
            is State.Success -> data.postValue(State.Success(state.data!!))
            is State.Failed -> data.postValue(State.Failed(state.error))
            is State.Loading -> data.postValue(State.Loading)
        }
    }
}