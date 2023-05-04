package com.red_velvet.marvel.ui.base

import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.data.util.State
import io.reactivex.rxjava3.core.Observable

data class LiveDataObservablePair<T>(
    val liveData: MutableLiveData<State<T>>,
    val stateObservable: Observable<State<T?>>
)
