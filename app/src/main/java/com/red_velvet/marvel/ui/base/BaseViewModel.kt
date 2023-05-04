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



    fun <T1, T2, T3> getDataForMultipleRequests(
        liveData1: MutableLiveData<State<T1>>,
        stateObservable1: Observable<State<T1?>>,
        liveData2: MutableLiveData<State<T2>>,
        stateObservable2: Observable<State<T2?>>,
        liveData3: MutableLiveData<State<T3>>,
        stateObservable3: Observable<State<T3?>>
    ) {
        Observable.combineLatest(
            stateObservable1.subscribeOn(Schedulers.io()),
            stateObservable2.subscribeOn(Schedulers.io()),
            stateObservable3.subscribeOn(Schedulers.io()),
            object : Function3<State<T1?>, State<T2?>, State<T3?>, Triple<State<T1>, State<T2>, State<T3>>> {
                override fun invoke(
                    p1: State<T1?>,
                    p2: State<T2?>,
                    p3: State<T3?>
                ): Triple<State<T1>, State<T2>, State<T3>> {
                    return Triple(p1 as State<T1>, p2 as State<T2>, p3 as State<T3>)
                }
            }
        ).observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onError = { error ->
                    val message = error.message.toString()
                    onGetDataError(liveData1, message)
                    onGetDataError(liveData2, message)
                    onGetDataError(liveData3, message)
                },
                onNext = { (state1, state2, state3) ->
                    handleUIState(liveData1, state1)
                    handleUIState(liveData2, state2)
                    handleUIState(liveData3, state3)
                }
            )
            .addTo(compositeDisposable)
    }

}