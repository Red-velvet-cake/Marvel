package com.red_velvet.marvel.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.red_velvet.marvel.data.util.State
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

abstract class BaseViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun <T> getData(liveData: MutableLiveData<State<T>>, stateObservable: Observable<State<T?>>) {
        try {
            stateObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onError = { onGetDataError(liveData, it.message.toString()) },
                    onNext = { handleUIState(liveData, it) },
                )
                .addTo(compositeDisposable)
        } catch (e: Exception) {
            onGetDataError(liveData, e.message.toString())
        }
    }

    private fun <T> onGetDataError(liveData: MutableLiveData<State<T>>, message: String) {
        liveData.postValue(State.Failed(message))
    }

    private fun <T> handleUIState(liveData: MutableLiveData<State<T>>, state: State<T?>) {
        when (state) {
            is State.Success -> liveData.postValue(State.Success(state.data!!))
            is State.Failed -> onGetDataError(liveData, state.error)
            is State.Loading -> liveData.postValue(State.Loading)
        }
    }
}