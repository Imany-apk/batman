package iman.mohammadpour.batman.utils

import android.content.Context
import android.widget.Toast

/**
 *  created by Iman Mohammadpour at 2020/Aug/03
 */

object SingleToast {

    private var toast: Toast? = null


    fun show(context: Context, text: String, duration: Int) {
        toast?.cancel()
        toast = Toast.makeText(context, text, duration)
        toast?.show()
    }

    fun cancel() {
        toast?.cancel()
        toast = null
    }

}