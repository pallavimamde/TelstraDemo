package com.techmahidra.telstrademo.utilties

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.techmahidra.telstrademo.R

/* *
* LoadImage - load image using @Glide lib
* #uri - Link to load image from path
* */
fun ImageView.loadImage(uri: String?) {
    val options = RequestOptions()
        .placeholder(R.mipmap.ic_launcher_round)
        .circleCrop()
        .error(R.mipmap.ic_launcher_round)
    Glide.with(this.context)
        .setDefaultRequestOptions(options)
        .load(uri)
        .into(this)
}