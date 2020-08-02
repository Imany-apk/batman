package iman.mohammadpour.batman.data.util

/**
 *  created by Iman Mohammadpour at 2020/Aug/02
 */

sealed class MovieResult<T> {

    data class OK<T>(val data: T?) : MovieResult<T>()
    data class Error<T>(val e: Throwable) : MovieResult<T>()

}