package iman.mohammadpour.batman.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import iman.mohammadpour.batman.data.entities.MovieSummary
import iman.mohammadpour.batman.data.repositories.MovieRepository
import iman.mohammadpour.batman.data.util.MovieResult
import iman.mohammadpour.batman.ui.base.BaseViewModel

/**
 *  created by Iman Mohammadpour at 2020/Aug/02
 */

class MainViewModel(
    private val repository: MovieRepository
) : BaseViewModel() {

    val onLiveMovies: LiveData<List<MovieSummary>>
        get() = _onLiveMovies
    private val _onLiveMovies = MutableLiveData<List<MovieSummary>>()

    val onMoviesError: LiveData<String>
        get() = _onMoviesError
    private val _onMoviesError = MutableLiveData<String>()


    fun onViewCreated(hasInternetAccess: Boolean) {
        repository.findAll().tell {
            if (it.isNotEmpty())
                _onLiveMovies.value = it
        }
        if (!hasInternetAccess) {
            _onMoviesError.value = "There is no Internet connection"
            return
        }
        repository.search("batman").tell {
            if (it is MovieResult.Error) {
                _onMoviesError.value = it.e.message
            }
        }
    }


}