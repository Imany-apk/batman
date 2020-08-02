package iman.mohammadpour.batman.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import iman.mohammadpour.batman.data.entities.Movie
import iman.mohammadpour.batman.data.repositories.MovieRepository
import iman.mohammadpour.batman.data.util.MovieResult
import iman.mohammadpour.batman.ui.base.BaseViewModel

/**
 *  created by Iman Mohammadpour at 2020/Aug/02
 */

class DetailViewModel(
    private val repository: MovieRepository
) : BaseViewModel() {

    val onLiveMovie: LiveData<Movie>
        get() = _onLiveMovie
    private val _onLiveMovie = MutableLiveData<Movie>()

    val onMovieError: LiveData<String>
        get() = _onMovieError
    private val _onMovieError = MutableLiveData<String>()


    fun onViewCreated(
        imdbID: String,
        hasInternetAccess: Boolean,
        fromLocal: Boolean
    ) {

        repository.findById(imdbID).tell {
            _onLiveMovie.postValue(it)
        }

        if (fromLocal)
            return

        if (!hasInternetAccess) {
            _onMovieError.postValue("There is no Internet connection")
            return
        }
        repository.getMovie(imdbID).tell {
            if (it is MovieResult.Error) {
                _onMovieError.postValue(it.e.message)
            }
        }
    }


}