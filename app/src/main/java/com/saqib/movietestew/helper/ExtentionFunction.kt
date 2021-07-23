package com.saqib.movietestew.helper

import android.content.Context
import android.media.Image
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.saqib.movietestew.R
import com.squareup.picasso.Picasso

fun ImageView.loadImage(context: Context, url: String?) {
    url?.let {
        Picasso
            .get()
            .load(it)
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_background)
            .into(this)
    }
}

@BindingAdapter("lazy_image")
fun  loadImage(imageView: ImageView, url: String?) {
    url?.let {
        Picasso
            .get()
            .load(it)
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_background)
            .into(imageView)
    }
}


