package com.red_velvet.marvel.ui.utils

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class GlideImageListener(
    private val loadingPlaceholder: LottieAnimationView?,
    private val errorPlaceholder: Drawable?,
    private val imageView: ImageView,
) : RequestListener<Drawable> {
    override fun onLoadFailed(
        e: GlideException?,
        model: Any?,
        target: Target<Drawable>?,
        isFirstResource: Boolean
    ): Boolean {
        loadingPlaceholder?.visibility = View.GONE
        imageView.visibility = View.VISIBLE
        imageView.setImageDrawable(errorPlaceholder)
        return false
    }

    override fun onResourceReady(
        resource: Drawable?,
        model: Any?,
        target: Target<Drawable>?,
        dataSource: DataSource?,
        isFirstResource: Boolean
    ): Boolean {
        loadingPlaceholder?.visibility = View.GONE
        imageView.visibility = View.VISIBLE
        return false
    }
}