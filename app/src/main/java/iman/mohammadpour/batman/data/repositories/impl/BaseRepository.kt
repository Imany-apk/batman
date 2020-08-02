package iman.mohammadpour.batman.data.repositories.impl

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import iman.mohammadpour.batman.data.entities.Rsp
import iman.mohammadpour.batman.data.repositories.BaseRepo
import iman.mohammadpour.batman.data.util.MovieResult
import iman.mohammadpour.batman.data.util.SingleLiveEvent
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

/**
 *  created by Iman Mohammadpour at 2020/Aug/02
 */

open class BaseRepository : BaseRepo {

    enum class FlowStatusCode(val value: Int) {
        OK(200),
    }

    override fun <T : Rsp> remote(
        single: Single<Response<T>>,
        onError: ((Throwable) -> Unit)?,
        onSuccess: ((T?) -> Unit)?
    ): LiveData<MovieResult<T>> {

        val liveData = SingleLiveEvent<MovieResult<T>>()

        Schedulers.io().scheduleDirect(
            ApiRunner(single, liveData, onError, onSuccess)
        )

        return liveData
    }

    override fun db(callable: () -> Unit) {
        Schedulers.io().scheduleDirect(callable)
    }


    class ApiRunner<T : Rsp>(
        private val single: Single<Response<T>>,
        private val liveData: MutableLiveData<MovieResult<T>>,
        private val onError: ((Throwable) -> Unit)?,
        private val onSuccess: ((T?) -> Unit)?
    ) : Runnable {

        @SuppressLint("CheckResult")
        override fun run() {

            single.subscribe({ rsp ->
                when (rsp.code()) {
                    FlowStatusCode.OK.value -> with(rsp.body()) {
                        onSuccess?.invoke(this)
                        liveData.postValue(MovieResult.OK(this))
                    }
                    else -> {
                        val e = Throwable("something went wrong - code ${rsp.code()}")
                        throwError(e)
                    }
                }
            }, {
                throwError(it)
            })
        }

        private fun throwError(e: Throwable) {
            onError?.invoke(e)
            liveData.postValue(MovieResult.Error(e))
        }

    }
}