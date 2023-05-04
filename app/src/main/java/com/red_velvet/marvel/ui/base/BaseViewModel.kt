package com.red_velvet.marvel.ui.base

import android.util.Log
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
        Log.d("SADEQMHANA", "onGetComicsError: ${message}")
        liveData.postValue(State.Failed(message))
    }

    private fun <T> handleUIState(liveData: MutableLiveData<State<T>>, state: State<T?>) {
        when (state) {
            is State.Success -> liveData.postValue(State.Success(state.data!!))
            is State.Failed -> onGetDataError(liveData, state.error)
            is State.Loading -> liveData.postValue(State.Loading)
        }
    }


    fun getDataForMultipleRequests(vararg pairs: LiveDataObservablePair<*>) {
        val observables = pairs.map {
            it.stateObservable.subscribeOn(Schedulers.io())
        }

        val combinedFunction: (Array<Any>) -> List<Pair<MutableLiveData<State<Any?>>, State<Any?>>> = { states ->
            states.mapIndexed { index, state ->
                (pairs[index].liveData as MutableLiveData<State<Any?>>) to (state as State<Any?>)
            }
        }

        Observable.combineLatest(observables, combinedFunction)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onError = { error ->
                    val message = error.message.toString()
                    pairs.forEach { pair -> onGetDataError(pair.liveData as MutableLiveData<State<Any>>, message) }
                },
                onNext = { combinedStates: List<Pair<MutableLiveData<State<Any?>>, State<Any?>>> ->
                    combinedStates.forEach { (liveData, state) ->
                        handleUIState(liveData, state)
                    }
                }
            )
            .addTo(compositeDisposable)
    }

}

