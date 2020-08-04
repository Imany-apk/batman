package iman.mohammadpour.batman.extensions

import android.content.Context
import android.net.ConnectivityManager

/**
 *  created by Iman Mohammadpour at 2020/Aug/02
 */

fun Context.hasInternetAccess(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    return cm!!.activeNetworkInfo != null
}