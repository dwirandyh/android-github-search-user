package com.dwirandyh.cermatiproject.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.dwirandyh.cermatiproject.R

class ViewBindingAdapter {

    companion object {
        @BindingAdapter("photoUrl")
        @JvmStatic
        fun loadPhotoFromUrl(view: ImageView, url: String?) {
            url?.let {
                Glide.with(view.context)
                    .load(it)
                    .placeholder(R.drawable.ic_account)
                    .error(R.drawable.ic_error)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(view)
            }
        }
    }
}