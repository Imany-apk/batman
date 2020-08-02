package iman.mohammadpour.batman.data.repositories

import androidx.lifecycle.LiveData
import iman.mohammadpour.batman.data.entities.Movie
import iman.mohammadpour.batman.data.entities.MovieSummary
import iman.mohammadpour.batman.data.entities.SearchResult
import iman.mohammadpour.batman.data.util.MovieResult

/**
 *  created by Iman Mohammadpour at 2020/Aug/02
 */

interface MovieRepository {

    // remote
    fun search(searchKey: String): LiveData<MovieResult<SearchResult>>

    fun getMovie(imdbID: String): LiveData<MovieResult<Movie>>

    // db
    fun findAll(): LiveData<List<MovieSummary>>

    fun findById(id: String): LiveData<Movie>


}