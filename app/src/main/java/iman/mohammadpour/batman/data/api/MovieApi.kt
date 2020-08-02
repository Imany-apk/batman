package iman.mohammadpour.batman.data.api

import iman.mohammadpour.batman.data.entities.Movie
import iman.mohammadpour.batman.data.entities.SearchResult
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *  created by Iman Mohammadpour at 2020/Aug/02
 */

interface MovieApi {

    @GET
    fun search(
        @Query("apiKey") apiKey: String = "3e974fca",
        @Query("s") s: String = "batman"
    ): Single<Response<SearchResult>>

    @GET
    fun getMovieDetail(
        @Query("apiKey") apiKey: String = "3e974fca",
        @Query("i") id: String
    ): Single<Response<Movie>>


}