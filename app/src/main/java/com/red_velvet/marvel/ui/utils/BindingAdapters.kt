package com.red_velvet.marvel.ui.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.red_velvet.marvel.R
import com.red_velvet.marvel.data.remote.dto.ThumbnailDto
import com.red_velvet.marvel.domain.util.toUrl
import com.red_velvet.marvel.ui.base.BaseAdapter

@BindingAdapter(value = ["app:showWhenLoading"])
fun <T> showWhenLoading(view: View, state: State<T>?) {
    if (state is State.Loading) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}

@BindingAdapter(value = ["app:showWhenError"])
fun <T> showWhenError(view: View, state: State<T>?) {
    if (state is State.Failed) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}

@BindingAdapter(value = ["app:showWhenSuccess"])
fun <T> showWhenSuccess(view: View, state: State<T>?) {
    if (state is State.Success) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}

@BindingAdapter(value = ["app:imageUrl"])
fun loadImage(view: ImageView, thumbnail: ThumbnailDto?) {
    Glide.with(view).load(thumbnail?.toUrl())
        .thumbnail(Glide.with(view).load(R.raw.loading))
        .fitCenter()
        .centerCrop()
        .into(view)
}

@BindingAdapter(value = ["app:imageUrl"])
fun loadImage(view: ImageView, imageUrl: String?) {
    Glide.with(view).load(imageUrl)
        .thumbnail(Glide.with(view).load(R.raw.loading))
        .fitCenter()
        .centerCrop()
        .into(view)
}

@BindingAdapter(value = ["app:setItems"])
fun <T> setRecyclerItems(view: RecyclerView, items: List<T>?) {
    if (items != null) {
        (view.adapter as BaseAdapter<T>).setItems(items)
    } else {
        (view.adapter as BaseAdapter<T>).setItems(emptyList())
    }
}

@BindingAdapter("app:showWhenData")
fun showWhenData(view: View, isEmpty: Boolean) {
    if (isEmpty) {
        view.visibility = View.GONE
    } else {
        view.visibility = View.VISIBLE
    }
}

@BindingAdapter("app:showWhenEmpty")
fun <T> showWhenEmpty(view: View, state: State<List<T>?>?) {
    if (state is State.Success) {
        if (state.data.isNullOrEmpty()) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.GONE
        }
    } else {
        view.visibility = View.GONE
    }
}