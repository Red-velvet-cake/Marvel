package com.red_velvet.marvel.ui.utils

import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import com.red_velvet.marvel.data.model.Thumbnail
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

fun <T : Any> Single<T>.applySchedulers(): Single<T> {
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun <T : Any> Observable<T>.applySchedulers(): Observable<T> {
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun Thumbnail.toUrl() = this.path + "." + this.extension

fun Fragment.onBackPressed(function: () -> Unit) {
    requireActivity().onBackPressedDispatcher.addCallback(requireActivity()) {
        function()
    }
}