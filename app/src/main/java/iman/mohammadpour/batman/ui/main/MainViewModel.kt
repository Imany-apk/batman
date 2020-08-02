package iman.mohammadpour.batman.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import iman.mohammadpour.batman.data.entities.MovieSummary
import iman.mohammadpour.batman.data.repositories.MovieRepository
import iman.mohammadpour.batman.data.util.MovieResult

/**
 *  created by Iman Mohammadpour at 2020/Aug/02
 */

class MainViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    val onLiveMovies: LiveData<List<MovieSummary>>
        get() = _onLiveMovies
    private val _onLiveMovies = MutableLiveData<List<MovieSummary>>()

    val onMoviesError: LiveData<String>
        get() = _onMoviesError
    private val _onMoviesError = MutableLiveData<String>()


    fun onViewCreated(hasInternetAccess: Boolean) {
        repository.findAll().observeForever { _onLiveMovies.postValue(it) }
        if (!hasInternetAccess) {
            _onMoviesError.postValue("There is no Internet connection")
            return
        }
        repository.search("batman").observeForever {
            if (it is MovieResult.Error) {
                _onMoviesError.postValue(it.e.message)
            }
        }
    }


}