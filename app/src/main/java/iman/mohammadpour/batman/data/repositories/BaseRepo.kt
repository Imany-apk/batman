package iman.mohammadpour.batman.data.repositories

import androidx.lifecycle.LiveData
import iman.mohammadpour.batman.data.entities.Rsp
import iman.mohammadpour.batman.data.util.MovieResult
import io.reactivex.Single
import retrofit2.Response

/**
 *  created by Iman Mohammadpour at 2020/Aug/02
 */

interface BaseRepo {

    fun <T : Rsp> remote(
        single: Single<Response<T>>,
        onError: ((Throwable) -> Unit)? = null,
        onSuccess: ((T?) -> Unit)? = null
    ): LiveData<MovieResult<T>>

    fun db(callable: () -> Unit)

}