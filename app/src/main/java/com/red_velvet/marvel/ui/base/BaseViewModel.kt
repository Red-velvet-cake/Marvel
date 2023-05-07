package com.red_velvet.marvel.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.red_velvet.marvel.ui.utils.Navigation
import com.red_velvet.marvel.ui.utils.State
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

abstract class BaseViewModel : ViewModel() {

    val compositeDisposable = CompositeDisposable()

    protected val _navigation: MutableLiveData<Navigation> = MutableLiveData()
    val navigation: LiveData<Navigation> = _navigation

    fun navigateBack() {
        _navigation.postValue(Navigation.Back)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun <T> bindStateUpdates(
        stateObservable: Observable<State<T>>,
        onError: (Throwable) -> Unit,
        onNext: (State<T>) -> Unit
    ) {
        try {
            stateObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onError = onError,
                    onNext = onNext,
                )
                .addTo(compositeDisposable)
        } catch (e: Exception) {
            onError(e)
        }
    }

}

