package iman.mohammadpour.batman.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

/**
 *  created by Iman Mohammadpour at 2020/Aug/02
 */

open class BaseViewModel : ViewModel() {


    private val communications = mutableMapOf<LiveData<*>, Observer<*>>()

    open fun <T> LiveData<T>.tell(callback: (T) -> Unit) {
        Observer<T> { callback.invoke(it) }.apply {
            observeForever(this)
            communications[this@tell] = this
        }
    }

    override fun onCleared() {
        communications.forEach {
            it.key.removeObserver(it.value as Observer<in Any>)
        }
        super.onCleared()
    }

}