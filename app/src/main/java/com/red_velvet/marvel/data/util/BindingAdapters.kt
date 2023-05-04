package com.red_velvet.marvel.data.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.red_velvet.marvel.data.model.Thumbnail
import com.red_velvet.marvel.ui.base.BaseAdapter

@BindingAdapter(value = ["app:showWhenLoading"])
fun <T> showWhenLoading(view: View, state: State<T>?){
    if (state is State.Loading){
        view.visibility = View.VISIBLE
    }else{
        view.visibility = View.GONE
    }
}

@BindingAdapter(value = ["app:showWhenError"])
fun <T> showWhenError(view: View, state: State<T>?){
    if (state is State.Failed){
        view.visibility = View.VISIBLE
    }else{
        view.visibility = View.GONE
    }
}

@BindingAdapter(value = ["app:showWhenSuccess"])
fun <T> showWhenSuccess(view: View, state: State<T>?){
    if (state is State.Success){
        view.visibility = View.VISIBLE
    }else{
        view.visibility = View.GONE
    }
}

@BindingAdapter(value = ["app:imageUrl"])
fun loadImage(view:ImageView,thumbnail: Thumbnail?){
    Glide.with(view).load(thumbnail?.toUrl()).into(view)
}

@BindingAdapter(value = ["app:setItems"])
fun <T> setRecyclerItems(view: RecyclerView, items:List<T>?){
    if (items !=null){
        (view.adapter as BaseAdapter<T>).setItems(items)
    }else{
        (view.adapter as BaseAdapter<T>).setItems(emptyList())
    }
}