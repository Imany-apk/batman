package iman.mohammadpour.batman.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy

/**
 *  created by Iman Mohammadpour at 2020/Aug/02
 */

fun ImageView.load(url: String) {
    Glide.with(context).load(url)
        .priority(Priority.HIGH)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}